/**
 * Created by user on 2015/8/22.
 */
backOfficeApp.controller("OrganizationController", OrganizationController);
function OrganizationController($scope, $modal, $log, $translatePartialLoader, $translate, Restangular, OrganizationService, OrganizationMoveService) {
    $translatePartialLoader.addPart("organization");
    $translate.refresh();

    var tree = $("#organizationTree");
    var zTreeObj;
    var nodeMoveSetting = {
        targetNode: undefined,
        treeNode: undefined,
        moveType: undefined
    };
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

    OrganizationService.getList().then(function (data) {
        $scope.organizationList = data;
        $scope.initTree();
        //$scope.rowCollection = $scope.organizationList;
    });

    $scope.initTree = function () {
        $.fn.zTree.init(tree, treeSetting, $scope.organizationList);
        zTreeObj = $.fn.zTree.getZTreeObj("organizationTree");
        zTreeObj.expandAll(true);
        $scope.initTreeContextMenu();
    };

    $scope.initTreeContextMenu = function () {
        tree.contextMenu({
            selector: ".zTreeItem",
            build: function (element) {
                var treeId = element.attr("id").replace("_a", "");
                var moveNodes = zTreeObj.getNodes();
                var currentNode = zTreeObj.getNodeByTId(treeId);
                if (currentNode.level != 0) {
                    moveNodes = currentNode.getParentNode().children;
                }
                nodeMoveSetting.moveNodes = moveNodes;
                nodeMoveSetting.treeNode = currentNode;

                zTreeObj.selectNode(currentNode);
                var menu = {};
                menu[Action.NewNode] = {
                    name: $translate.instant("add_organization"),
                    callback: function () {
                        $scope.currentAction = Action.NewNode;
                        if (currentNode.level == 0) {
                            $scope.editNewObject();
                        } else {
                            $scope.editNewObject(currentNode.getParentNode());
                        }
                        $scope.editTitle = $translate.instant("add_organization");
                        $scope.showEditModal();
                    }
                };
                menu[Action.NewChildNode] = {
                    name: $translate.instant("add_sub_organization"),
                    callback: function () {
                        $scope.currentAction = Action.NewChildNode;
                        $scope.editNewObject(currentNode);
                        $log.info(currentNode);
                        $scope.editTitle = currentNode.organization_name + ":"+$translate.instant("add_sub_organization");
                        $scope.showEditModal();
                    }
                };
                menu[Action.MoveFirst] = {
                    name: $translate.instant("moveTop"),
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[0];
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveFirst;
                        OrganizationMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveUp] = {
                    name: $translate.instant("moveUp"),
                    disabled: !currentNode.getPreNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getPreNode();
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveUp;
                        OrganizationMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveDown] = {
                    name: $translate.instant("moveDown"),
                    disabled: !currentNode.getNextNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getNextNode();
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveDown;
                        OrganizationMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveLast] = {
                    name: $translate.instant("moveBottom"),
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[moveNodes.length - 1];
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveLast;
                        OrganizationMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu['sep2'] = '';
                menu[Action.Edit] = {
                    name: $translate.instant("edit"),
                    callback: function () {
                        $scope.currentAction = Action.Edit;
                        $scope.editNode = Restangular.copy(currentNode);
                        $scope.editTitle = currentNode.organization_name + ":"+$translate.instant("edit");
                        $scope.showEditModal();
                    }
                };
                menu[Action.Remove] = {
                    name: $translate.instant("delete"),
                    callback: function () {
                        $log.info(currentNode);
                        Restangular.copy(currentNode).remove().then(function (data) {
                            var selectedNode = zTreeObj.getSelectedNodes()[0];
                            zTreeObj.removeNode(selectedNode);
                        });
                    }
                };
                return {
                    autoHide: false,
                    items: menu
                };
            }
        });
    };

    $scope.editNewObject = function (parentNode) {
        $scope.editNode = {};
        if (parentNode) {
            if (parentNode.isParent) {
                $scope.editNode.sequence = parentNode.children.length;
            } else {
                $scope.editNode.sequence = 0;
            }
            $scope.editNode.parentOrganizationId = parentNode.organizationId;
        } else {
            var rootNodes = zTreeObj.getNodes();
            if (rootNodes) {
                $scope.editNode.sequence = rootNodes.length;
            } else {
                $scope.editNode.sequence = 0;
            }
            $scope.editNode.parentOrganizationId = undefined;
        }
    };

    $scope.onNodeMove = function (data) {
        for (var i = 0; i < data.length; i++) {
            var node = zTreeObj.getNodeByParam("organizationId", data[i].organizationId);
            node.sequence = data[i].sequence;
        }
        zTreeObj.moveNode(nodeMoveSetting.targetNode, nodeMoveSetting.treeNode, nodeMoveSetting.moveType, true);
    };

    $scope.addNodeClick = function () {
        $scope.currentAction = Action.NewNode;

        var selectedNodes = zTreeObj.getSelectedNodes();

        var selectedNode = undefined;
        if (selectedNodes.length > 0) {
            selectedNode = selectedNodes[0];
        }
        if (selectedNode && selectedNode.level == 0) {
            $scope.editNewObject();
        } else {
            $scope.editNewObject(selectedNode);
        }
        $scope.editTitle = $translate.instant("add_organization");
        $scope.showEditModal();
    };

    function EditModalController($scope){
        $scope.save = function () {
            switch ($scope.currentAction) {
                case Action.NewNode:
                case Action.NewChildNode:
                    OrganizationService.post( $scope.editNode).then(function (data) {
                        $scope.editNode = data;
                        $scope.editModalClose();
                    });
                    break;
                case Action.Edit:
                    $scope.editNode.put().then(function (data) {
                        $scope.editModalClose();
                    });
                    break;
            }
        };

        $scope.editModalClose = function () {
            var selectedNode = undefined;
            switch ($scope.currentAction) {
                case Action.NewNode:
                    if ($scope.editNode.parentOrganizationId) {
                        var parent_node = zTreeObj.getNodeByParam("organizationId", $scope.editNode.parentOrganizationId);
                        zTreeObj.addNodes(parent_node, $scope.editNode, true);
                    } else {
                        zTreeObj.addNodes(null, $scope.editNode, true);
                    }
                    break;
                case Action.NewChildNode:
                    selectedNode = zTreeObj.getSelectedNodes()[0];
                    zTreeObj.addNodes(selectedNode, $scope.editNode, true);
                    zTreeObj.expandNode(selectedNode, true);
                    break;
                case Action.Edit:
                    selectedNode = zTreeObj.getSelectedNodes()[0];
                    selectedNode.organization_code = $scope.editNode.organization_code;
                    selectedNode.organization_name = $scope.editNode.organization_name;
                    zTreeObj.updateNode(selectedNode);
                    break;
            }
            $scope.hideEditModal();
        };
    }

    var editModal = $modal({
        scope: $scope,
        controller: EditModalController,
        templateUrl:"organizationEdit.html",
        show:false
    });

    $scope.showEditModal = function() {
        editModal.$promise.then(editModal.show);
    };
    $scope.hideEditModal = function() {
        editModal.$promise.then(editModal.hide);
    };

}