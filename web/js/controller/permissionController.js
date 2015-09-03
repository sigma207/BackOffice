/**
 * Created by user on 2015/8/6.
 */
backOfficeApp.controller("PermissionController", PermissionController);
function PermissionController($scope, $modal, $log, Restangular, PermissionService, locale) {
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
    $scope.modalTitle = "新增節點";

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
                        $scope.modalTitle = "新增節點";
                        $scope.showModal();
                    }
                };
                menu[Action.NewChildNode] = {
                    name: "新增子節點",
                    callback: function () {
                        $log.info(Action.NewChildNode);
                        $scope.currentAction = Action.NewChildNode;
                        $scope.editNewObject(currentNode);
                        $scope.modalTitle = currentNode[locale.zh_TW] + ":新增子節點";
                        $scope.showModal();
                    }
                };
                menu[Action.MoveFirst] = {
                    name: "移到最上",
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[0];
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveFirst;
                        PermissionService.post(nodeMoveSetting,{move:"true"}).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveUp] = {
                    name: "往上移",
                    disabled: !currentNode.getPreNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getPreNode();
                        nodeMoveSetting.moveType = "prev";
                        nodeMoveSetting.moveAction = Action.MoveUp;
                        PermissionService.post(nodeMoveSetting,{move:"true"}).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveDown] = {
                    name: "往下移",
                    disabled: !currentNode.getNextNode(),
                    callback: function () {
                        nodeMoveSetting.targetNode = currentNode.getNextNode();
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveDown;
                        PermissionService.post(nodeMoveSetting,{move:"true"}).then($scope.onNodeMove);
                    }
                };
                menu[Action.MoveLast] = {
                    name: "移到最下",
                    callback: function () {
                        nodeMoveSetting.targetNode = moveNodes[moveNodes.length - 1];
                        nodeMoveSetting.moveType = "next";
                        nodeMoveSetting.moveAction = Action.MoveLast;
                        PermissionService.post(nodeMoveSetting,{move:"true"}).then($scope.onNodeMove);
                    }
                };
                menu['sep2'] = '';
                menu[Action.Edit] = {
                    name: "編輯",
                    callback: function () {
                        $scope.currentAction = Action.Edit;
                        $scope.editNode = Restangular.copy(currentNode);
                        locale.convertDashToBaseLine($scope.editNode.permissionNameMap);
                        $scope.modalTitle = currentNode.name + ":編輯";
                        $scope.showModal();
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
            $scope.editNode.parentPermissionId = parentNode.permissionId;
        } else {
            var rootNodes = zTreeObj.getNodes();
            if (rootNodes) {
                $scope.editNode.sequence = rootNodes.length;
            } else {
                $scope.editNode.sequence = 0;
            }
            $scope.editNode.parentPermissionId = undefined;
        }
    };

    $scope.onNodeMove = function (data) {
        for (var i = 0; i < data.length; i++) {
            var node = zTreeObj.getNodeByParam("permissionId", data[i].permissionId);
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
        $scope.modalTitle = "新增節點";
        $scope.showModal();
    };

    $scope.modalClose = function () {
        var selectedNode = undefined;
        locale.node($scope.editNode, $scope.editNode);
        switch ($scope.currentAction) {
            case Action.NewNode:
                if ($scope.editNode.parentPermissionId) {
                    var parent_node = zTreeObj.getNodeByParam("permissionId", $scope.editNode.parentPermissionId);
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
        $scope.hideModal();
    };

    function ModalController($scope){
        $scope.save = function () {
            locale.convertBaseLineToDash($scope.editNode.permissionNameMap);
            switch ($scope.currentAction) {
                case Action.NewNode:
                case Action.NewChildNode:
                    PermissionService.post( $scope.editNode).then(function (data) {
                        $log.info(data);
                        $log.info($scope.editNode);
                        $scope.editNode = data;
                        $scope.modalClose();
                    });
                    break;
                case Action.Edit:
                    $scope.editNode.put().then(function (data) {
                        $scope.modalClose();
                    });
                    break;
            }
        };
    }

    var modal = $modal({
        scope: $scope,
        controller: ModalController,
        templateUrl:"permissionEdit.html",
        show:false
    });

    $scope.showModal = function() {
        modal.$promise.then(modal.show);
    };
    $scope.hideModal = function() {
        modal.$promise.then(modal.hide);
    };
}