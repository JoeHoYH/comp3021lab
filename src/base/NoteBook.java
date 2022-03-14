package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteBook implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders=new ArrayList<Folder>();
	}
	
	public NoteBook(String file) {		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		NoteBook n=null;
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			n =(NoteBook) ois.readObject();
			ois.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
		this.folders=n.folders;
		
	}
	
	public boolean createImageNote(String folderName,String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName,String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName,note);
	}
	
	public boolean createTextNote(String folderName,String title,String content) {
		TextNote note = new TextNote(title,content);
		return insertNote(folderName,note);
	}
	
	public ArrayList<Folder> getFolders() {
		return folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for(Folder f1:folders) {
			if(f1.getName().equals(folderName))
				f=f1;
		}
		if(f==null) {
			f = new Folder(folderName);
			folders.add(f);
		}
		for(Note n:f.getNotes()) {
			if(n.equals(note))
			{ System.out.println("Creating note "+note.getTitle()+" under folder "+folderName+" failed");
				return false;
			}}
		f.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords){
		ArrayList<Note> result = new ArrayList<Note>();
		for(Folder f:folders) {
			result.addAll(f.searchNotes(keywords));
		}
		return result;
	}
	
	public boolean save(String file) {
		FileOutputStream fos = null;
		ObjectOutputStream ous = null;
		
		try {
			fos = new FileOutputStream(file);
			ous = new ObjectOutputStream(fos);
			ous.writeObject(this);
			ous.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
