class BinomialNode {
    int key;         // Значение (приоритет)
    int degree;      // Степень узла (кол-во детей)
    BinomialNode parent;     // Родитель
    BinomialNode child;      // Первый ребенок (самый левый)
    BinomialNode sibling;    // Брат (правый сосед)

    public BinomialNode(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }
}