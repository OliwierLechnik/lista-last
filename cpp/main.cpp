#include <iostream>
#include <string>
#include <sstream>

template<class T>
class Node {
private:
    T value;
    Node* left;
    Node* right;
    Node* parrent;

    Node(T v, Node* p) {
        this->value = v;
        this->parrent = p;
        this->left = nullptr;
        this->right = nullptr;
    }

public:
    Node(T value) {
        this->value = value;
        this->parrent = nullptr;
        this->left = nullptr;
        this->right = nullptr;
    }


    friend std::ostream &operator<<(std::ostream &os, const Node &node) {
        os << "value: " << node.value;
        return os;
    }

    void insert(T value) {
        if(value < this->value) {
            if(this->left == nullptr) {
                this->left = new Node<T>(value, this);
            } else {
                this->left->insert(value);
            }
        } else {
            if(this->right == nullptr) {
                this->right = new Node<T>(value, this);
            } else {
                this->right->insert(value);
            }
        }
    }

    Node* search(T value){
        if(this->value == value)
            return this;

        Node* l = nullptr;
        Node* r = nullptr;

        if(this->left == nullptr) { l = nullptr; }
        else { l = this->left->search(value); }

        if(this->right == nullptr) { l = nullptr; }
        else { r = this->right->search(value); }

        return l == nullptr ? r : l;
    }

    std::string serialize() {
        std::ostringstream oss;
        oss << this->value << "{";
        oss << (this->left ? this->left->serialize() : "");
        oss << ",";
        oss << (this->right ? this->right->serialize() : "");
        oss << "}";
        return oss.str();
    }

};

int main() {
    Node <int> node(5);
    node.insert(3);
    node.insert(7);
    node.insert(6);
    node.insert(8);

    Node <std::string> str("beta");
    str.insert("duzy");
    str.insert("ala ma kota");
    str.insert("ciastko");

    auto s = node.search(3);
    std::cout << str.serialize() << std::endl;


    return 0;
}
