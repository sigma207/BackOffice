/**
 * Created by user on 2015/8/6.
 */
backendApp.controller("PermissionController", PermissionController);
function PermissionController($scope, $modal, $log, Restangular, PermissionService, PermissionMoveService, locale) {
    $log.info("PermissionController!!");
    var tree = $("#permissionTree");
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
            }
        }
    };
    $scope.editSize = undefined;
    $scope.editTitle = "新增節點";

    PermissionService.getList().then(function (data) {
        $scope.permissionList = data;
        locale.formatPermissionList($scope.permissionList);
        $scope.initTree();
    });

    $scope.initTree = function () {
        tree = $("#permissionTree");
        $.fn.zTree.init(tree, treeSetting, $scope.permissionList);
        zTreeObj = $.fn.zTree.getZTreeObj("permissionTree");
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
                    name: "新增節點",
                    callback: function () {
                        $log.info(Action.NewNode);
                        $scope.currentAction = Action.NewNode;
                        if (currentNode.level == 0) {
                            $scope.editNewObject();
                        } else {
                            $scope.editNewObject(currentNode.getParentNode());
                        }
                        $scope.editTitle = "新增節點";
                        $scope.open();
                    }
                };
                menu[Action.NewChildNode] = {
                    name: "新增子節點",
                    callback: function () {
                        $log.info(Action.NewChildNode);
                        $scope.currentAction = Action.NewChildNode;
                        $scope.editNewObject(currentNode);
                        $scope.editTitle = currentNode[locale.zh_TW] + ":新增子節點";
                        $scope.open();
                    }
                };
                menu[Action.MoveFirst] = {
                    name: "移到最上",
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[0];
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveFirst;
                        PermissionMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveUp] = {
                    name: "往上移",
                    disabled: !currentNode.getPreNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getPreNode();
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveUp;
                        PermissionMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveDown] = {
                    name: "往下移",
                    disabled: !currentNode.getNextNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getNextNode();
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveDown;
                        PermissionMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveLast] = {
                    name: "移到最下",
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[moveNodes.length - 1];
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveLast;
                        PermissionMoveService.post(nodeMoveSetting).then($scope.onNodeMove);
                    }
                };
                menu['sep2'] = '';
                menu[Action.Edit] = {
                    name: "編輯",
                    callback: function () {
                        $scope.currentAction = Action.Edit;
                        $scope.editNode = Restangular.copy(currentNode);
                        locale.convertDashToBaseLine($scope.editNode.permissionNameMap);
                        $scope.editTitle = currentNode.name + ":編輯";
                        $scope.open();
                    }
                };
                menu[Action.Remove] = {
                    name: "移除",
                    callback: function () {
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
            $scope.editNode.parent_permission_id = parentNode.permission_id;
        } else {
            var rootNodes = zTreeObj.getNodes();
            if (rootNodes) {
                $scope.editNode.sequence = rootNodes.length;
            } else {
                $scope.editNode.sequence = 0;
            }
            $scope.editNode.parent_permission_id = undefined;
        }
    };

    $scope.onNodeMove = function (data) {
        for (var i = 0; i < data.length; i++) {
            var node = zTreeObj.getNodeByParam("permission_id", data[i].permission_id);
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
        $scope.editTitle = "新增節點";
        $scope.open();
    };

    $scope.open = function () {
        //$scope.editSize = "sm";
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'permissionEdit.html',
            controller: 'permissionEditCtrl',
            size: $scope.editSize,
            resolve: {
                editNode: function () {
                    return $scope.editNode;
                },
                title: function () {
                    return $scope.editTitle;
                },
                currentAction: function () {
                    return $scope.currentAction;
                }
            }
        });

        modalInstance.result.then(function (editNode) {

            var selectedNode = undefined;
            $scope.editNode = editNode;
            locale.node($scope.editNode, $scope.editNode);
            switch ($scope.currentAction) {
                case Action.NewNode:
                    if ($scope.editNode.parent_permission_id) {
                        var parent_node = zTreeObj.getNodeByParam("permission_id", $scope.editNode.parent_permission_id);
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
                    selectedNode.permission_code = $scope.editNode.permission_code;
                    selectedNode.permissionNameMap = $scope.editNode.permissionNameMap;
                    locale.node(selectedNode, $scope.editNode);
                    zTreeObj.updateNode(selectedNode);
                    break;
            }
        }, function () {
            //$log.info('Modal dismissed at: ' + new Date());
        });
    };
}

backendApp.controller('permissionEditCtrl', function ($scope, $modalInstance, $log, PermissionService, locale, title, editNode, currentAction) {
    $scope.title = title;
    $scope.editNode = editNode;
    $scope.ok = function () {
        locale.convertBaseLineToDash($scope.editNode.permissionNameMap);
        switch (currentAction) {
            case Action.NewNode:
            case Action.NewChildNode:
                PermissionService.post( $scope.editNode).then(function (data) {
                    $modalInstance.close(data);
                });
                break;
            case Action.Edit:
                $scope.editNode.put().then(function (data) {
                    $modalInstance.close(data);
                });
                break;
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

});