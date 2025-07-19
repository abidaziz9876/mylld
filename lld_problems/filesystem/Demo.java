package lld_problems.filesystem;



/*
The Composite Design Pattern is a structural design pattern that is used
to treat individual objects and compositions of objects uniformly. 
It’s particularly useful when you want to build a tree-like structure
where both individual elements (leaf nodes) and groups of elements (composite nodes) 
should be treated the same way.
*/
public class Demo {
    public static void main(String[] args) {
        File file1 = new File("Resume.docx", 40);
        File file2 = new File("Photo.png", 120);
        File file3 = new File("Video.mp4", 1500);

        Directory documents = new Directory("Documents");
        documents.add(file1);

        Directory media = new Directory("Media");
        media.add(file2);
        media.add(file3);

        Directory root = new Directory("Root");
        root.add(documents);
        root.add(media);

        root.show("");
        System.out.println("Total size: " + root.getSize() + "KB");
    }
}
