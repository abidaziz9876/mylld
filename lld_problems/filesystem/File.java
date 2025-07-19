package lld_problems.filesystem;


// Leaf
class File implements FileSystem {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void show(String indent) {
        System.out.println(indent + "- File: " + name + " (" + size + "KB)");
    }

    public int getSize() {
        return size;
    }
}

