package data;


/**
 * JournalEntry
 * Data object used to represent a single journal entry
 * 
 * @version 0.9
 */
public class JournalEntry {
    private String title;
    private String context;
    private String date;
    private String time;
    private int ID;

    /**
     * @param String title: title of entry
     * @param String context: context of entry 
     * @param String date: date in mm/dd/yyyy format (leading zeros optional)
     * @param String time: time in hh:mm format in 24 hour format
     */
    public JournalEntry(String title, String context, String date, String time, int ID){
        this.title = title;
        this.context = context;
        //TODO: ensuring a leading zero is easier for parsing and should be done here
        this.date = date;
        this.time = time;
        this.ID = ID;
    }

    /**
     * @return gets title of entry 
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * @return gets context
     */
    public String getContext(){
        return this.context;
    }

    /**
     * @return gets date of entry in mm/dd/yyyy format
     */
    public String getDate(){
        return this.date;
    }

    /**
     * @return gets time in hh:mm format in 24 hour time
     */
    public String getTime(){
        return this.time;
    }

    /**
     * @return returns sequential ID
     */
    public int getID(){
        return this.ID;
    }

}