/**
 * Created by user on 2015/8/7.
 */
backendApp.controller("UserController", UserController);
function UserController($scope, $translatePartialLoader, $translate, $log, $modal, UserService, RoleService, OrganizationService) {
    $translatePartialLoader.addPart("user");
    $translate.refresh();

    $scope.getUserList = function () {
        UserService.getList().then(function (data) {
            $scope.rowCollection = data;
            $scope.getRoleList();
        });
    };

    $scope.getRoleList = function () {
        RoleService.getList().then(function (data) {
            $scope.roleList = data;
        });
    };

    $scope.queryClick = function () {
        $scope.getUserList();
    };

    $scope.removeUserClick = function (row) {
        row.remove().then(function () {
            var index = $scope.rowCollection.indexOf(row);
            $scope.rowCollection.splice(index, 1);
        });
    };

    $scope.editUserClick = function (row) {
    };

    $scope.allocateRoleClick = function (row) {
        $scope.editObj = row;
        row.getList("userRoles").then(function (data) {
            $scope.userRoleList = data;
            $scope.modalTitle = $scope.editObj.loginId+":"+$translate.instant("allocateRole");
            $scope.showAllocateModal();
        });
    };

    $scope.addUserClick = function () {
        $scope.currentAction = Action.Add;
        $scope.editObj = {};
        $scope.editObj.parentBoUser = $scope.loginUser;
        $scope.modalTitle = $translate.instant("addUser");
        $scope.showEditModal();
    };

    //editModal
    $scope.editModalClose = function () {
        $scope.getUserList();
        $scope.hideEditModal();
    };

    function EditModalController($scope){
        $scope.selectOrganization = function () {
            $scope.showOrganizationModal();
        };

        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.Add:
                    UserService.post( $scope.editObj).then(function (data) {
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

        //organizationModal
        $scope.organizationModalClose = function () {
            $scope.hideOrganizationModal();
        };

        function OrganizationController($scope){
            var zTreeObj;
            $scope.init = function() {
                $scope.title = $translate.instant("org.select");
                if($scope.editObj.parentBoUser.organization){
                    $scope.editObj.parentBoUser.organization.getList("with_children").then(function (data) {
                        $scope.initTree(data);
                    });
                }else{
                    OrganizationService.getList().then(function (data) {
                        $scope.initTree(data);
                    });
                }
            };

            $scope.initTree = function (data) {
                var tree = $("#organizationTree");
                var treeSetting = {
                    data: {
                        simpleData: {
                            enable: true
                        },
                        key:{
                            name:"organizationName"
                        }
                    }
                };
                $.fn.zTree.init(tree, treeSetting, data);
                zTreeObj = $.fn.zTree.getZTreeObj("organizationTree");
                zTreeObj.expandAll(true);
            };

            $scope.save = function () {
                $scope.editObj.boOrganization = zTreeObj.getSelectedNodes()[0];
                $scope.organizationModalClose();
            };
        }
        var organizationModal = $modal({
            scope: $scope,
            controller: OrganizationController,
            templateUrl:"selectOrganization.html",
            show:false
        });

        $scope.showOrganizationModal = function() {
            organizationModal.$promise.then(organizationModal.show);
        };
        $scope.hideOrganizationModal = function() {
            organizationModal.$promise.then(organizationModal.hide);
        };
        /////////////////////////////////////////////////
    }

    var editModal = $modal({
        scope: $scope,
        controller: EditModalController,
        templateUrl:"userEdit.html",
        show:false
    });

    $scope.showEditModal = function() {
        editModal.$promise.then(editModal.show);
    };
    $scope.hideEditModal = function() {
        editModal.$promise.then(editModal.hide);
    };

    //allocateModal
    $scope.allocateModalClose = function () {
        $scope.getUserList();
        $scope.hideAllocateModal();
    };

    function AllocateModalController($scope){
        $log.info("AllocateModalController");
        $scope.selectedRoleList = [];
        $scope.init = function() {
            var i, count;
            var roleMap = {};
            for (i = 0, count = $scope.userRoleList.length; i < count; i++) {
                roleMap[$scope.userRoleList[i].roleId] = 0;
            }
            for (i = 0, count = $scope.roleList.length; i < count; i++) {
                if (typeof roleMap[$scope.roleList[i].roleId] !== typeof undefined) {
                    $scope.selectedRoleList.push($scope.roleList[i]);
                }
            }
            $scope.rowCollection = $scope.roleList;//沒有和userList的rowCollection衡突到
        };

        $scope.save = function () {
            var list = $scope.selectedRoleList;
            $scope.editObj.boUserRoleList = [];
            for(var i= 0,count=list.length;i<count;i++){
                $scope.editObj.boUserRoleList.push({userId: $scope.editObj.userId, roleId: list[i].roleId});
            }
            $scope.editObj.post("userRoles").then(function (data) {
                $scope.allocateModalClose();
            });
        };
    }

    var allocateModal = $modal({
        scope: $scope,
        controller: AllocateModalController,
        templateUrl:"allocateRole.html",
        show:false
    });

    $scope.showAllocateModal = function() {
        allocateModal.$promise.then(allocateModal.show);
    };
    $scope.hideAllocateModal = function() {
        allocateModal.$promise.then(allocateModal.hide);
    };

    $scope.getUserList();
}
