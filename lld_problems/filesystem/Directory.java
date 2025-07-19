package lld_problems.filesystem;

import java.util.ArrayList;
import java.util.List;

// Composite
class Directory implements FileSystem {
    private String name;
    private List<FileSystem> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystem node) {
        children.add(node);
    }

    public void show(String indent) {
        System.out.println(indent + "+ Directory: " + name);
        for (FileSystem node : children) {
            node.show(indent + "  ");
        }
    }

    public int getSize() {
        int total = 0;
        for (FileSystem node : children) {
            total += node.getSize();
        }
        return total;
    }
}
