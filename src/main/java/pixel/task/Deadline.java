package pixel.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pixel.DateTimeParser;
import pixel.PixelException;

/**
 * Represents a deadline task. A deadline task is a task with a specific deadline date
 */
public class Deadline extends Task {
    private String type = "D";

    /**
     * Constructs a new Deadline object with the given description.
     *
     * @param description The description of the deadline task.
     * @throws PixelException If the description is empty or does not match the required format.
     */
    public Deadline(String description) throws PixelException {
        super(modifyDescription(description));
    }

    /**
     * Constructs a new Deadline object with the given description and completion status.
     *
     * @param description The description of the deadline task.
     * @param done The completion status of the deadline task.
     */
    public Deadline(String description, String done) {
        super(description, done);
    }

    /**
     * Modifies the description of the deadline task by extracting the deadline date.
     *
     * @param des The original description of the deadline task.
     * @return The modified description of the deadline task.
     * @throws PixelException If the description is empty or does not match the required format.
     */
    private static String modifyDescription(String des) throws PixelException {
        if (des.length() == 0) {
            throw new PixelException("OH NO!!! The description of Deadline cannot be empty!");
        }
        String regex = "(.*?) /by (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(des);
        if (matcher.find()) {
            String initalDes = matcher.group(1);
            DateTimeParser time = new DateTimeParser(matcher.group(2));
            return String.format("%s by: %s", initalDes, time);
        } else {
            throw new PixelException("Event should be of this format: {description} /by {date}");
        }
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return this.type;
    }

}
