package com.example;

/* Data Structures Example */

//  node -> node -> node -> empty
LinkedList<T> = UNode(T item, LinkedList<T> next) | EmptyUNode();

//  node(node(node(empty,empty),empty),empty)
BinaryTree<T> = BNode(T item, BinaryTree<T> left, BinaryTree<T> right) | EmptyBNode();

Optional<T> = Exists(T item) | NoExists(); // optional value
Either<U, V> = Left(U item) | Right(V item); // Common variant: Return<T> = Success(T value) | Failure(String reason);

