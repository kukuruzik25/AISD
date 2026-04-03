import java.util.ArrayList;
import java.util.List;

public class BinomialHeap {
    private BinomialNode head; // указатель на самый левый корень в списке корней
    private BinomialNode minNode; // указатель на узел с минимальным ключом

    public BinomialHeap() {
        this.head = null;
        this.minNode = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Слияние (объединяет два списка корней так, чтобы степени шли по порядку)
    private BinomialNode merge(BinomialNode heap1, BinomialNode heap2) {
        if (heap1 == null) return heap2;
        if (heap2 == null) return heap1;
        BinomialNode newHead = null;
        BinomialNode tail = null;
        BinomialNode h1 = heap1;
        BinomialNode h2 = heap2;
        // пока оба списка не пусты, соединяем их в порядке возрастания степени
        while (h1 != null && h2 != null) {
            BinomialNode temp;
            if (h1.degree <= h2.degree) {
                temp = h1;
                h1 = h1.sibling;
            } else {
                temp = h2;
                h2 = h2.sibling;
            }
            if (newHead == null) {
                newHead = temp;
                tail = temp;
            } else {
                tail.sibling = temp;
                tail = temp;
            }
        }
        // добавляем остатки
        if (h1 != null) tail.sibling = h1;
        else if (h2 != null) tail.sibling = h2;

        return newHead;
    }

    // Вспомогательная функция: связать два дерева одинаковой степени (корень тот - у кого ключ меньше)
    private void link(BinomialNode child, BinomialNode parent) {
        // child становится подчиненным parent
        child.parent = parent;
        child.sibling = parent.child;
        parent.child = child;
        parent.degree++;
    }

    // Объединение ("умный" мерж + выравнивание (как перенос единиц в сложении))
    public void union(BinomialHeap other) {
        if (other == null || other.head == null) return;
        // сначала мержим списки корней
        this.head = merge(this.head, other.head);
        other.head = null; // мы забрали данные, обнуляем
        if (this.head == null) return;
        // далее проходим по списку и объединяем деревья одинаковой степени
        BinomialNode prev = null;
        BinomialNode curr = this.head;
        BinomialNode next = curr.sibling;
        while (next != null) {
            // если степени разные или (степени одинаковые, но у следующего за следующим такая же — случай трех подряд)
            // мы должны объединять только пары, чтобы не испортить порядок
            if (curr.degree != next.degree || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                // степени равны — объединяем
                if (curr.key <= next.key) {
                    // curr - корень
                    curr.sibling = next.sibling;
                    link(next, curr);
                } else {
                    // next - корень
                    if (prev == null) {
                        this.head = next;
                    } else {
                        prev.sibling = next;
                    }
                    link(curr, next);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
        // обновляем указатель на минимум
        updateMinNode();
    }

    // ДОБАВЛЕНИЕ
    public void insert(int key) {
        // создаем новую кучу с одним узлом
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.head = new BinomialNode(key);
        tempHeap.minNode = tempHeap.head;
        // объединяем новую кучу с текущей (за O(log n))
        this.union(tempHeap);
    }

    // ПОИСК МИНИМУМА
    public BinomialNode findMin() {
        if (isEmpty()) return null;
        return minNode;
    }

    // Вспомогательная функция для обновления указателя на минимум
    private void updateMinNode() {
        if (head == null) {
            minNode = null;
            return;
        }
        BinomialNode current = head;
        BinomialNode min = head;
        while (current != null) {
            if (current.key < min.key) {
                min = current;
            }
            current = current.sibling;
        }
        this.minNode = min;
    }

    // УДАЛЕНИЕ МИНИМУМА
    public BinomialNode extractMin() {
        if (isEmpty()) return null;
        // находим узел с минимумом и предыдущий перед ним в списке корней
        BinomialNode prev = null;
        BinomialNode curr = head;
        BinomialNode minPrev = null;
        BinomialNode minCurr = head;
        // ищем минимум
        while (curr != null) {
            if (curr.key < minCurr.key) {
                minCurr = curr;
                minPrev = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }
        // удаляем minCurr из списка корней
        if (minPrev == null) {
            // минимум был первым элементом
            head = minCurr.sibling;
        } else {
            minPrev.sibling = minCurr.sibling;
        }
        // теперь нужно добавить детей удаленного корня обратно в кучу
        // дети minCurr образуют "обратную" кучу (потому что sibling связывает в порядке убывания степени)
        BinomialHeap childHeap = new BinomialHeap();
        // разворачиваем список детей, чтобы степени шли по возрастанию (для merge)
        BinomialNode child = minCurr.child;
        BinomialNode nextChild;
        List<BinomialNode> reversedChildren = new ArrayList<>();
        while (child != null) {
            nextChild = child.sibling;
            child.sibling = null;
            child.parent = null;
            reversedChildren.add(0, child); // вставляем в начало для разворота
            child = nextChild;
        }
        // строим кучу из детей
        BinomialHeap tempHeap = new BinomialHeap();
        for (BinomialNode node : reversedChildren) {
            BinomialHeap single = new BinomialHeap();
            single.head = node;
            // обновляем min для временной кучи
            single.updateMinNode();
            tempHeap.union(single);
        }
        // объединяем основную кучу (без min) с кучей из детей
        this.union(tempHeap);
        // обновляем minNode
        updateMinNode();
        return minCurr;
    }

    public void printHeap() {
        System.out.println("Binomial Heap:");
        if (head == null) {
            System.out.println("Empty");
            return;
        }
        BinomialNode current = head;
        while (current != null) {
            System.out.print("Tree of degree " + current.degree + ": ");
            printTree(current);
            System.out.println();
            current = current.sibling;
        }
    }

    private void printTree(BinomialNode node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        printTree(node.child);
        printTree(node.sibling);
    }
}