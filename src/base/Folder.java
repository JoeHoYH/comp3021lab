package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>,java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name) {
		this.name=name;
		notes=new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords){
		String[] parsedKeywords = keywords.toLowerCase().split(" ");
		ArrayList<String[]> keywordsList = new ArrayList<String[]>();
		for (int i =0; i < parsedKeywords.length;i++) {
			if(i+1!=parsedKeywords.length&&parsedKeywords[i+1].equals("or")) {
				keywordsList.add(new String[] {parsedKeywords[i],parsedKeywords[i+2]});
				i+=2;
			}
			else {
				keywordsList.add(new String[] {parsedKeywords[i]});
			}
		}
		
		ArrayList<Note> result = new ArrayList<Note>();	
		for(Note note: notes) {
			if(note instanceof ImageNote) {
				outer:for(String[] sl:keywordsList) {
					for(String s:sl) {
						if(note.getTitle().toLowerCase().contains(s)) {
							break;
						}else if(s==sl[sl.length-1]) {
							break outer;
						}
					}
					if(sl==keywordsList.get(keywordsList.size()-1)) {
						result.add(note);
					}
				}
			}
			else {
				outer:for(String[] sl:keywordsList) {
					for(String s:sl) {
						if(note.getTitle().toLowerCase().contains(s) || ((TextNote)note).getContent().toLowerCase().contains(s)) {
							break;
						}else if(s==sl[sl.length-1]) {
							break outer;
						}
					}
					if(sl==keywordsList.get(keywordsList.size()-1)) {
						result.add(note);
					}
				}
			}
		}
		return result;
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Folder other = (Folder) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		int nText=0;
		int nImage=0;
		for(Note note:notes) {
			if(note instanceof TextNote) {
				nText++;
			}
			else if(note instanceof ImageNote){
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public int compareTo(Folder o) {
		return name.compareTo(o.name);
	}
	
}
