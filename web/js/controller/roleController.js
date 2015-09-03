/**
 * Created by user on 2015/8/6.
 */
backOfficeApp.controller("RoleController", RoleController);
function RoleController($scope, $translatePartialLoader, $translate, $log, $modal, $timeout, Restangular, RoleService, PermissionService, locale) {
    $translatePartialLoader.addPart("role");
    $translate.refresh();
    $log.info("RoleController!!");
    //var roles = RoleRestangular.all("role");

    $scope.getRoleList = function () {
        RoleService.getList().then(function (data) {
            $scope.rowCollection = data;
            $scope.getPermissionList();
        });
    };

    $scope.getPermissionList = function () {
        PermissionService.getList().then(function (data) {
            $scope.permissionList = data;
            locale.formatPermissionList($scope.permissionList);
        });
    };

    $scope.queryClick = function () {
        $scope.getRoleList();
    };

    $scope.removeRoleClick = function (row) {
        row.remove().then(function () {
            var index = $scope.rowCollection.indexOf(row);
            $scope.rowCollection.splice(index, 1);
        });
    };

    $scope.editRoleClick = function (row) {
        $scope.currentAction = Action.Edit;
        $scope.editObj = Restangular.copy(row);
        $scope.modalTitle =  $scope.editObj.roleCode+":"+$translate.instant("editRole");
        $scope.showEditModal();
    };

    $scope.allocatePermissionClick = function (row) {
        row.getList("permission").then(function (data) {
            row.permissionList = data;
            $scope.editObj = row;
            $scope.modalTitle =  $scope.editObj.roleCode+":"+$translate.instant("allocatePermission");
            $scope.showAllocateModal();
        });
    };

    $scope.addRoleClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = {};
        $scope.modalTitle = $translate.instant("addRole");
        $scope.showEditModal();
    };

    $scope.editModalClose = function () {
        $scope.getRoleList();
        $scope.hideEditModal();
    };

    function EditModalController($scope){
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    RoleService.post( $scope.editObj).then(function (data) {
                        $scope.editModalClose();
                    });
                    break;
                case Action.Edit:
                    $scope.editObj.put().then(function (data) {
                        $scope.editModalClose();
                    });
                    break;
            }
        };
    }

    var editModal = $modal({
        scope: $scope,
        controller: EditModalController,
        templateUrl:"roleEdit.html",
        show:false
    });

    $scope.showEditModal = function() {
        editModal.$promise.then(editModal.show);
    };
    $scope.hideEditModal = function() {
        editModal.$promise.then(editModal.hide);
    };

    $scope.allocateModalClose = function () {
        $scope.getRoleList();
        $scope.hideAllocateModal();
    };

    function AllocateModalController($scope){
        $log.info("AllocateModalController");

        var zTreeObj;
        $timeout(function(){
            var tree = $("#permissionTree");
            var treeSetting = {
                check: {
                    enable: true
                }
            };

            $.fn.zTree.init(tree, treeSetting, $scope.permissionList);
            zTreeObj = $.fn.zTree.getZTreeObj("permissionTree");
            zTreeObj.checkAllNodes(false);
            var node = undefined;
            for (var i = 0; i < $scope.editObj.permissionList.length; i++) {
                node = zTreeObj.getNodeByParam("permissionId", $scope.editObj.permissionList[i].permissionId);
                zTreeObj.checkNode(node, true, false);
            }
            zTreeObj.expandAll(true);
        });

        $scope.save = function () {
            var checkedNodes = zTreeObj.getCheckedNodes();
            var roleId = $scope.editObj.roleId;
            var count = checkedNodes.length;
            $scope.editObj.boRolePermissionList = [];
            for (var i = 0; i < count; i++) {
                $scope.editObj.boRolePermissionList.push({permissionId: checkedNodes[i].permissionId, roleId: roleId});
            }
            $scope.editObj.post("permission").then(function () {
                $scope.allocateModalClose();
            });
        };
    }

    var allocateModal = $modal({
        scope: $scope,
        controller: AllocateModalController,
        templateUrl:"allocatePermission.html",
        show:false
    });

    $scope.showAllocateModal = function() {
        allocateModal.$promise.then(allocateModal.show);
    };
    $scope.hideAllocateModal = function() {
        allocateModal.$promise.then(allocateModal.hide);
    };

    $scope.getRoleList();
}