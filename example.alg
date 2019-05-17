package com.example;

LinkedList<T> = UNode(T item, LinkedList<T> next) | EmptyUNode();
BinaryTree<T> = BNode(T item, BinaryTree<T> left, BinaryTree<T> right) | EmptyBNode();
Optional<T> = Exists(T item) | NoExists();
Either<U, V> = Left(U item) | Right(V item);

