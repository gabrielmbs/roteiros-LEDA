package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		System.out.println("left rotation aqui seu bosta");
		BSTNode<T> pivot = ((BSTNode<T>) node.getRight());
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		node.setRight(pivot.getLeft());
		pivot.getLeft().setParent(node);
		pivot.setLeft(node);
		return pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		System.out.println("right rotation aqui seu bosta");
		BSTNode<T> pivot = ((BSTNode<T>) node.getLeft());
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		node.setLeft(((BSTNode<T>) pivot.getRight()));
		pivot.getRight().setParent(node);
		pivot.setRight(node);
		return pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
