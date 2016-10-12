import java.io.*;
import java.util.*;

public class RectangularGrid {
	public long countRectangles(int width, int height) {
        long res = 0;
        if(width < height) {
            width = width + height;
            height = width - height;
            width = width - height;
        }
        for(int i = 1; i <= height ; i++)
            for(int j = i + 1; j <= width; j++) {
                res += (width - j + 1) * (height - i + 1);
                if(height - j + 1 > 0 && width - i + 1 > 0)
                    res += (height - j + 1) * (width - i + 1);
            }
		return res;
	}
}
