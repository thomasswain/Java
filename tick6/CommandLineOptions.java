public class CommandLineOptions {

public static String WORLD_TYPE_LONG = "--long";
public static String WORLD_TYPE_AGING = "--aging";
public static String WORLD_TYPE_ARRAY = "--array";
private String worldType = null;
private Integer index = null;
private String source = null;

public CommandLineOptions(String[] args) throws CommandLineException {
//TODO: parse "args" to update the private fields "worldType", 
// "index" and "source" with the correct values, if any.

if (args.length==0){throw new CommandLineException("Error: No Arguments Found");}


}

public String getWorldType() {return worldType;}

public Integer getIndex() {return index;}

public String getSource() {return source;}

}