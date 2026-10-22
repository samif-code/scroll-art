import java.util.Random;

public class ScrollArt {
    static final int WIDTH = getTerminalWidth() - 1;
    static final int SHARK_WIDTH = 19;
    static final int SHARK_HEIGHT = 8;
    static final int FISH_WIDTH = 12;
    static final int FISH_HEIGHT = 10;
    static final int BONES_WIDTH = 17;
    static final int BONES_HEIGHT = 12;
    static final Random rand = new Random();
    // NO OVERLAPS
    // 1. Skip every w (width) characters instead of every column to reduce
    // horizontal overlap
    // 2. How do you reduce overlap vertically? Remember previous row at the
    // specific width

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int iterations = 0;
        char[][] nextRows = new char[19][WIDTH]; // store upcoming rows
        for (int i = 0; i < nextRows.length; i++) {
            nextRows[i] = emptyRow();
            
        }
        char[] prevRow = nextRows[0];

        while (true) {
             // At each lane, 7% chance to add a new image if it fits and the area is clear
            for (int x = 0; x < WIDTH; x += SHARK_WIDTH+1) {
                if (rand.nextDouble() < 0.07) {
                    char[][] img = randomImage();
                    if (fits(img, x) && isClear(nextRows, prevRow, img, x)) {
                        loadNextRowsWithImage(nextRows, img, x);
                    }
                }
            }
           
            // Print and remove the top row
            System.out.println(new String(nextRows[0]));
            prevRow = nextRows[0]; // update prev row
            // Shift all rows up
            shiftRowsUp(nextRows);
            Thread.sleep(200); // Delay in ms
            long time = System.currentTimeMillis() - startTime;
            iterations++;

            //System.err.println("average time per frame: " + (time / iterations) + " ms");
        }
    }

    // pick an image first, then build only that one
    static char[][] randomImage() {
        int choice = rand.nextInt(3);
        if (choice == 0) {
            return getShark();
        }
        if (choice == 1) {
            return getFish();
        }
        return getBones();
    
    }

    // does the image fit horizontally if placed at column x?
    static boolean fits(char[][] img, int x) {
        return x + img[0].length <= WIDTH;
    }

    // #2: checking the row just printed and every upcoming row the image will
    // cover are blank across the image's full width
    static boolean isClear(char[][] nextRows, char[] prevRow, char[][] img, int x) {
        int w = img[0].length;
        for (int i = x; i < x + w; i++) {
            if (prevRow[i] != ' ') {
                return false;
            }
        }
        for (int iy = 0; iy < img.length; iy++) {
            for (int i = x; i < x + w; i++) {
                if (nextRows[iy][i] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void loadNextRowsWithImage(char[][] nextRows, char[][] img, int x) {
        for (int iy = 0; iy < img.length; iy++) { // height
            for (int ix = 0; ix < img[iy].length; ix++) { // width
                nextRows[iy][x + ix] = img[iy][ix];
            }
        }
    }

    static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) {
            nextRows[i - 1] = nextRows[i];
        }
        nextRows[nextRows.length - 1] = emptyRow();
    }

    static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            row[i] = ' ';
        }
        return row;
    }



    // rename your function here
    static char[][] getShark() {
        char[][] img = new char[SHARK_HEIGHT][SHARK_WIDTH];
        // fill with empty space
        for (int y = 0; y < SHARK_HEIGHT; y++) {
            for (int x = 0; x < SHARK_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //big fish
        //top 
         
        img[2][7] = '_';
        img[2][8] = '_'; 
        img[2][9] = '_';
        img[2][10] = '_';    
        img[1][11] = '|';   
        img[2][12] = '_';   
        img[2][13] = '_'; 
        img[2][14] = '_';   
        img[2][15] = '_';   
        img[2][16] = '_';   
        img[2][17] = '_';   

        img[3][4] = '\\';     
        img[3][6] = '/';  
        //eye
        img[3][17] = 'o';  

        img[3][18] = '\\';  

        img[4][5] = '>';  
        img[4][18] = '>';  
        img[5][4] = '/';  

        img[5][18] = '/';  
        img[5][17] = '0';  
        img[5][18] = '/';  
//bottem
        img[6][6] = '\\'; 
        img[6][7] = '_';
        img[6][8] = '_'; 
        img[6][9] = '_';
        img[6][10] = '_';    
        img[7][11] = '|';   
        img[6][12] = '_';   
        img[6][13] = '_'; 
        img[6][14] = '_';   
        img[6][15] = '_';   
        img[6][16] = '_';   
        img[6][17] = '_'; 
 








        return img;
    }

    static char[][] getFish() {
        char[][] img = new char[FISH_HEIGHT][FISH_WIDTH];
        // fill with empty space
        for (int y = 0; y < FISH_HEIGHT; y++) {
            for (int x = 0; x < FISH_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //small fish
        //top fin
        img[1][6] = '\\';
        img[2][3] = '\\';
        img[2][7] = '\\';
        img[3][8] = '\\';
        img[4][5] = '\\';
        img[4][9] = '\\';
        img[4][5] = '\\';
        img[4][8] = '/';
        img[5][6] = '>';
        img[5][7] = '=';
        img[5][10] = 'o';
        img[5][11] = '>';
        img[6][5] = '/';
        img[6][8] = '\\';
        img[6][9] = '/';
        img[7][4] = '/';
        img[7][8] = '/';
        img[8][3] = '/';
        img[8][7] = '/';
        

        


       

        return img;
    }
static char[][] getBones() {
        char[][] img = new char[BONES_HEIGHT][BONES_WIDTH];
        // fill with empty space
        for (int y = 0; y < BONES_HEIGHT; y++) {
            for (int x = 0; x < BONES_WIDTH; x++) {
                img[y][x] = ' ';
            }
        }
        // then fill individual characters
        //dead fish
        img[5][11] = '>';
        img[5][12] = ')';
        img[5][13] = ')';
        img[5][14] = ')';
        img[5][15] = 'x';
        img[5][16] = '>';

        //sea weeds

        img[9][4] = '_';
        img[9][3] = '_';
        img[9][4] = ')';
        img[8][3] = ')';
        img[7][4] = ')';
        img[6][4] = ')';



        

        
     
        

        


       

        return img;
    }
    public static int getTerminalWidth() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

    private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

}
