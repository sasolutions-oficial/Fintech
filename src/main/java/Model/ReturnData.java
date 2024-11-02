package Model;

public class ReturnData {
	private boolean isSaved;
	private String message;
	
	public ReturnData() {};
	
	public ReturnData(boolean isSaved, String message){
		this.isSaved = isSaved;
		this.message = message;
	}
	
	 public boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }
    
	public String getMessage() {
        return message;
    }
	
    public void setMessage(String message) {
        this.message = message;
    }
	
}