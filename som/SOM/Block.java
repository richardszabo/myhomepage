/* Block - Decompiled by JODE
 * http://www.informatik.uni-oldenburg.de/~delwi/jode/jode.html
 * Send comments or bug reports to jochen@gnu.org
 */
package SOM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Block
{
    public boolean isShown;

    private double corner1X;
    private double corner1Y;
    private double corner2X;
    private double corner2Y;
    
    private static double[][] bl = {
	{
	    0.7, 0.8, 1.0, 1.0
	}, {
	    -0.1, -1.0, 0.1, -0.2
	}, {
	    -1.0, 0.0, -0.5, 0.5
	}
    };
    
    public Block(int i_1_1_) {
	isShown = false;

	corner1X = bl[i_1_1_][0];
	corner1Y = bl[i_1_1_][1];
	corner2X = bl[i_1_1_][2];
	corner2Y = bl[i_1_1_][3];
    }
    
    public boolean isInBlock(Input input_1_3_) {
	double[] ds_2_4_ = input_1_3_.getFeatures();
	if( !isShown || 
            !((corner1X - ds_2_4_[0]) * (corner2X - ds_2_4_[0]) < 0.0) || 
	    !((corner1Y - ds_2_4_[1]) * (corner2Y - ds_2_4_[1]) < 0.0) )
	    return false;
	return true;
    }
    
    public void drawBlock(Graphics graphics_1_6_, Dimension dimension_2_7_, Color background) {
	Point point_3_8_ = new Point((int) ((double) dimension_2_7_.width * (corner1X + 1.0) / 2.0), 
				 (int) ((double) dimension_2_7_.height * (corner1Y + 1.0) / 2.0));
	Point point_4_9_ = new Point((int) ((double) dimension_2_7_.width * (corner2X + 1.0) / 2.0), 
				 (int) ((double) dimension_2_7_.height * (corner2Y + 1.0) / 2.0));
	Color color_5_10_ = graphics_1_6_.getColor();

	if( isShown ) {
	    graphics_1_6_.setColor(Color.white);
	    graphics_1_6_.drawRect(point_3_8_.x, point_3_8_.y, point_4_9_.x - point_3_8_.x, point_4_9_.y - point_3_8_.y);
	    //graphics_1_6_.setColor(Color.gray);
	    //graphics_1_6_.fillRect(point_3_8_.x, point_3_8_.y, point_4_9_.x - point_3_8_.x, point_4_9_.y - point_3_8_.y);
	} else {
	    graphics_1_6_.setColor(background);
	    graphics_1_6_.drawRect(point_3_8_.x, point_3_8_.y, point_4_9_.x - point_3_8_.x, point_4_9_.y - point_3_8_.y);
	    //graphics_1_6_.fillRect(point_3_8_.x, point_3_8_.y, point_4_9_.x - point_3_8_.x, point_4_9_.y - point_3_8_.y);
	}
	graphics_1_6_.setColor(color_5_10_);
    }
}



