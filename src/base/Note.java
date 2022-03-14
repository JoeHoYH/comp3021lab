package base;
import java.util.Date;
public class Note implements Comparable<Note>,java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Date date;
	private String title;
	 
	public Note(String title) {
		this.title=title;
		date=new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return title.equals(other.title);
	}

	@Override
	public int compareTo(Note o) {
		return date.compareTo(o.date);
	}
	
	public String toString(){
		return date.toString()+ "\t"+title;
	}

}
