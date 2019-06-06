package com.example.union;

/* Data Structures Example */

//  node -> node -> node -> empty
@SuppressWarnings("unchecked")
LinkedList<T extends Comparable<T> & Cloneable> = UNode(final T item, volatile synchronized LinkedList<T> next) | EmptyUNode();

//  node(node(node(empty,empty),empty),empty)
BinaryTree<T> = BNode(final transient T item, volatile BinaryTree<T> left, volatile BinaryTree<T> right) | EmptyBNode();

@Deprecated
Optional<T> = Exists(T item) | NoExists(); // optional value
Either<U, V> = Left(U item) | Right(V item); // Common variant: Return<T> = Success(T value) | Failure(String reason);

