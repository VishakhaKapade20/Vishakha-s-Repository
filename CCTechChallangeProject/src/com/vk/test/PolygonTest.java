package com.vk.test;

class PolygonTest {

	// Define Infinite (Using INT_MAX
	// caused overflow problems)
	static int INF = 10000;

	static class Point {
		int x;
		int y;

		public Point(double d, double e) {
			this.x = (int) d;
			this.y = (int) e;
		}
	};

	// Given three colinear points p, q, r,
	// the function checks whether p is on the line or not
	
	static boolean onSegment(Point p, Point q, Point r) {
		if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) && q.y <= Math.max(p.y, r.y)
				&& q.y >= Math.min(p.y, r.y)) {
			return true;
		}
		return false;
	}

	// To find direction of ordered triplet (p, q, r).
	// The function returns following values
	// 0 --> p, q and r are colinear
	// 1 --> Clockwise
	// 2 --> antiClockwise
	static int direction(Point p, Point q, Point r) {
		int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

		if (val == 0) {
			return 0; // colinear
		}
		return (val > 0) ? 1 : 2; // clockwise  or anticlock wise
	}

	// The function that returns true if  line segment 'p1q1' and 'p2q2' intersect.
	static boolean isIntersect(Point p1, Point q1, Point p2, Point q2) {
		// Find the four directions  needed for 4 lines andpoints of other lines
		
		int o1 = direction(p1, q1, p2);
		int o2 = direction(p1, q1, q2);
		int o3 = direction(p2, q2, p1);
		int o4 = direction(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4) {
			return true;
		}

	
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1)) {
			return true;
		}

		// p1, q1 and p2 are colinear and  q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1)) {
			return true;
		}

		// p2, q2 and p1 are colinear and   p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2)) {
			return true;
		}

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
       if (o4 == 0 && onSegment(p2, q1, q2)) {
			return true;
		}

		// Doesn't fall in any of the above cases
		return false;
	}

	// Returns true if the point p lies inside the polygon[] with n vertices
	static boolean isInside(Point polygon[], int n, Point p) {
		// There must be at least 3 vertices in polygon[]
		if (n < 3) {
			return false;
		}

		// Create a point for line segment from p to infinite
		Point extreme = new Point(INF, p.y);

		// Count intersections of the above line with sides of polygon
		int count = 0, i = 0;
		do {
			int next = (i + 1) % n;

			// Check if the line segment from 'p' to 'extreme' intersects with the line
			// segment from 'polygon[i]' to 'polygon[next]'
			if (isIntersect(polygon[i], polygon[next], p, extreme)) {
			
				// If the point 'p' is colinear with line
				// segment 'i-next', then check if it lies on segment. If it lies, return true, otherwise false
				
				if (direction(polygon[i], p, polygon[next]) == 0) {
					return onSegment(polygon[i], p, polygon[next]);
				}

				count++;
			}
			i = next;
		} while (i != 0);

		// Return true if count is odd, false otherwise
		return (count % 2 == 1); // Same as (count%2 == 1)
	}

	
	public static void main(String[] args) {
		Point polygon1[] = { new Point(1, 0), new Point(8, 3), new Point(8, 8), new Point(1, 5) };
		int n = polygon1.length;
		Point p = new Point(3, 5);
		if (isInside(polygon1, n, p)) {
			System.out.println("Yes, Point p is inside the polygon");
		} else {
			System.out.println("No, Point p is  not inside the polygon");
		}
		
		
		Point polygon2[] = { new Point(-3,-2), new Point(-2,-0.8), new Point(0,1.2), new Point(2.2,0) };
		int n1 = polygon2.length;
		Point p1 = new Point(0,0);
		if (isInside(polygon1, n1, p1)) {
			System.out.println("Yes, Point p is inside the polygon");
		} else {
			System.out.println("No, Point p is  not inside the polygon");
		}
	}//end main method

}//end main class
