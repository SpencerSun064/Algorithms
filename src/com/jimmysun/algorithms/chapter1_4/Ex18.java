public class LocalMin
{
	public static void main(String[] args)
	{
		public static int localMinimum(int[] a) {
		    if (a == null || a.length == 0) {
		        return -1;
		    }
		    if (a.length == 1 || a[0] < a[1]) {
		        return 0;
		    }
		    if (a[a.length - 1] < a[a.length - 2]) {
		        return a.length - 1;
		    }

		    int mid = 0;
		    int left = 1;
		    int right = a.length - 2;
		    while (left < right) {
		        mid = (left + right) / 2;
		        if (a[mid - 1] < a[mid]) {
		            right = mid - 1;
		        } else if (a[mid + 1] < a[mid]) {
		            left = mid + 1;
		        } else {
		            return mid;
		        }
		    }
		    return left;
		}
	}
} 