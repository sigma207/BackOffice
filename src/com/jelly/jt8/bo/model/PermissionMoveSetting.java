package com.jelly.jt8.bo.model;

import java.util.List;

/**
 * Created by user on 2015/7/27.
 */
public class PermissionMoveSetting {
    List<BoPermission> moveNodes;
    BoPermission targetNode;
    BoPermission treeNode;
    String moveType;
    String moveAction;

    public List<BoPermission> getMoveNodes() {
        return moveNodes;
    }

    public void setMoveNodes(List<BoPermission> moveNodes) {
        this.moveNodes = moveNodes;
    }

    public BoPermission getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(BoPermission targetNode) {
        this.targetNode = targetNode;
    }

    public BoPermission getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(BoPermission treeNode) {
        this.treeNode = treeNode;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getMoveAction() {
        return moveAction;
    }

    public void setMoveAction(String moveAction) {
        this.moveAction = moveAction;
    }
}
