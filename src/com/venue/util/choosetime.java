package com.venue.util;

public class choosetime {
	public int[] selecttime(String venueNophours) {
		int[] temp = new int[3];

		Integer x = Integer.parseInt(venueNophours);
		int[] time = { 1, 2, 4 };
		for (int i = 0; i < time.length; i++) {
			if ((x & time[i]) == 0) {
				temp[i] = 0;
			} else {
				temp[i] = 1;
			}
		}
		return temp;
	}
}

