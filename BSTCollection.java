package DesignExcercise;

import java.util.ArrayList;
import java.util.List;


public class BSTCollection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] val = {10, 20, 5, 4, 40, 50};
		BinaryTree avl = new BinaryTree(35);
		try {
			for (int i : val)
				avl = avl.insert(i);
		} catch (BSTInsertException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print(avl);
		System.out.println("#####");
		avl = avl.delete(35);
		print(avl);
	}
	
	static void print(BinaryTree node) {
		if (node != null) {
			System.out.println(node.v.doubleValue());
			print(node.left);
			print(node.right);
		}
	}
}

class BinaryTree<V extends Number> {
	V v;
	BinaryTree<V> left;
	BinaryTree<V> right;
	BinaryTree<V> parent;
	public BinaryTree(V v) {
		this.v = v;
	}
	BinaryTree<V> insert(V v) throws BSTInsertException {
		if (this.v.doubleValue() > v.doubleValue()) {
			if (this.left == null)
				this.left = new BinaryTree(v);
			else
				this.left = this.left.insert(v);
		}
		else if (this.v.doubleValue() < v.doubleValue()){
			if (this.right == null)
				this.right = new BinaryTree(v);
			else
				this.right = this.right.insert(v);
		}
		else {
			throw new BSTInsertException();
		}
		return this;
	}
	
	BinaryTree findMax() {
		if (this.left == null && this.right == null) {
			return this;
		}
		else if (this.right == null) {
			return this;
		}
		else {
			return this.right.findMax();
		}
	}
	
	BinaryTree delete(V key) {
		if (this.v.equals(key)) {
			if (this.left == null && this.right == null) {
				return null;
			}
			else if (this.left == null) {
				return this.right;
			}
			else if (this.right == null) {
				return this.left;
			}
			else {
				BinaryTree node = this.left.findMax();
				this.v = (V) node.v;
				this.left = this.left.delete((V) node.v);
			}

		}
		else if (this.v.doubleValue() < key.doubleValue()) {
			if (this.right != null) {
				this.right = this.right.delete(key);
			}
		}
		else if (this.v.doubleValue() > key.doubleValue()) {
			if (this.left != null) {
				this.left = this.left.delete(key);
			}
		}
		return this;
	}
	
	void leftRotation() {
		
	}
	
	int getHeight(BinaryTree node) {
		if (node == null) {
			return 0;
		}
		return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
	}
}

class BSTInsertException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}

class AVLTree<V extends Number> extends BinaryTree<V> {
	int height;
	public AVLTree(V v) {
		super(v);
		// TODO Auto-generated constructor stub
		height = 1;
	}

	public static <V extends Number> BinaryTree<V> insert(AVLTree<V> node, V key) throws BSTInsertException {
		if (node == null)
			return new AVLTree<V>(key);
		if (key.doubleValue() < node.v.doubleValue()) {
			node.left = insert((AVLTree<V>) node.left, key);
		}
		else if (key.doubleValue() > node.v.doubleValue()){
			node.right = insert((AVLTree<V>) node.right, key);
		}
		else {
			throw new BSTInsertException();
		}
		node.height = Math.max(getHeight((AVLTree) node.left), 
								getHeight((AVLTree) node.right)) + 1;
		
		int diff = getHeight((AVLTree) node.left) 
					- getHeight((AVLTree) node.right);
		if (diff > 1 && key.doubleValue() < node.left.v.doubleValue()) {
			return rightRotation(node);
		}
		else if (diff > 1 && key.doubleValue() > node.left.v.doubleValue()) {
			node.left = leftRotation(node.left);
			return rightRotation(node);
		}
		else if (diff < -1 && key.doubleValue() < node.right.v.doubleValue()) {
			node.right = rightRotation(node.right);
			return leftRotation(node);
		}
		else if (diff < -1 && key.doubleValue() > node.right.v.doubleValue()) {
			return leftRotation(node);
		}
		return node;
	}
	
	static AVLTree rightRotation(BinaryTree node) {
		AVLTree x = (AVLTree) node.left;
		AVLTree y = (AVLTree) node;
		y.left = x.right;
		x.right = y;
		y.height = Math.max(getHeight((AVLTree) y.left), 
				getHeight((AVLTree) y.right)) + 1;
		x.height = Math.max(getHeight((AVLTree) x.left), 
				getHeight((AVLTree) x.right)) + 1;
		return x;
	}
	
	static AVLTree leftRotation(BinaryTree node) {
		AVLTree x = (AVLTree) node;
		AVLTree y = (AVLTree) node.right;
		x.right = y.left;
		y.left = x;
		y.height = Math.max(getHeight((AVLTree) y.left), 
				getHeight((AVLTree) y.right)) + 1;
		x.height = Math.max(getHeight((AVLTree) x.left), 
				getHeight((AVLTree) x.right)) + 1;
		return y;
	}
	
	static int getHeight(AVLTree node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}
}

class BlackRedTree<V extends Number> extends BinaryTree<V> {
	boolean red;
	BlackRedTree<V> parent;
	public BlackRedTree(V v) {
		super(v);
		this.red = true;
	}
	
	public static <V extends Number> BinaryTree 
		insert(BlackRedTree<V> node, V key) throws BSTInsertException {
		if (node == null)
			return new BlackRedTree<V>(key);
		if (key.doubleValue() < node.v.doubleValue()) {
			node.left = insert((BlackRedTree<V>) node.left, key);
		}
		else if (key.doubleValue() > node.v.doubleValue()) {
			node.right = insert((BlackRedTree<V>) node.right, key);
		}
		else {
			throw new BSTInsertException();
		}
		if (node.parent == null) {
			node.red = false;
		}
		else if (node.parent.parent != null && node.parent.red) {
			BlackRedTree<V> grandPa = node.parent.parent;
			BlackRedTree<V> parent = node.parent;
			BlackRedTree<V> uncle = (BlackRedTree<V>) (parent == grandPa.left ? grandPa.right : grandPa.left);
			
			if (uncle.red) {
				grandPa.red = true;
				uncle.red = false;
				parent.red = false;
			}
			else {
				
			}
		}
		return node;
	}
}
