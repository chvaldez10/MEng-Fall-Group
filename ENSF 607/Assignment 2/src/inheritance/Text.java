
public class Text {
    private String text;

	public Text(String text) {
       this.text = text;
	}
	
	
	public void setText(String newText){
		text = newText;
	}
	
	public String getText(){
		return text ;
	}
	
	@Override
	public String toString(){
		return (text);
	}
}
