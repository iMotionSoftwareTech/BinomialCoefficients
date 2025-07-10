import java.text.DecimalFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BinomialCoefficients {
    public static void main(String[] args) {
        // 1. Parse the command-line argument 'n'
        if (args.length != 1) {
            System.out.println("Usage: java BinomialCoefficients <n>");
            System.out.println("  <n>: The number of coin tosses (and the row to calculate).");
            return;
        }

        int n = Integer.parseInt(args[0]);

        // Input validation for n
        if (n < 0) {
            System.out.println("Error: The number of tosses (n) must be a non-negative integer.");
            return;
        }

        // 2. Create the two-dimensional ragged array 'a'
        // The array 'a' will have n+1 rows (from 0 to n).
        // Each row 'i' will have 'i+1' elements (from a[i][0] to a[i][i]).
        double[][] a = new double[n + 1][];

        // Initialize each row of the ragged array
        for (int i = 0; i <= n; i++) {
            a[i] = new double[i + 1];
        }

        // 3. Apply the base cases
        // a[i][0] = 0.0 for all i (from 0 to n)
        // This is implicitly handled by Java's default initialization of double arrays to 0.0,
        // but we explicitly set it for clarity and adherence to the rule.
        for (int i = 0; i <= n; i++) {
            a[i][0] = 0.0;
        }

        // a[1][1] = 1.0 (specific base case)
        if (n >= 1) {
            a[1][1] = 1.0;
        }

        // 4. Compute values in successive rows using the recurrence relation
        // We start from row i = 2 because rows 0 and 1 have been initialized by base cases.
        for (int i = 2; i <= n; i++) {
            // Calculate elements from k=1 up to k=i-1
            for (int k = 1; k < i; k++) {
                // a[n][k] = (a[n-1][k] + a[n-1][k-1]) / 2.0
                a[i][k] = (a[i - 1][k] + a[i - 1][k - 1]) / 2.0;
            }
            // Handle the rightmost element of the current row: a[i][i]
            // This corresponds to (a[i-1][i] + a[i-1][i-1]) / 2.0.
            // Since a[i-1][i] would be out of bounds for row i-1, it's implicitly 0.0.
            // So, a[i][i] = (0.0 + a[i-1][i-1]) / 2.0 = a[i-1][i-1] / 2.0.
            a[i][i] = a[i - 1][i - 1] / 2.0;
        }

        // 5. Print the probabilities for the given 'n' (the last row calculated)
        System.out.println("Probabilities for n = " + n + " tosses:");
        // Use DecimalFormat to ensure consistent output with a fixed number of decimal places
        DecimalFormat df = new DecimalFormat("0.000000"); // Example: 6 decimal places

        // Print each probability for k heads in n tosses
        for (int k = 0; k <= n; k++) {
            System.out.println("P(k=" + k + " heads) = " + df.format(a[n][k]));
        }
    }
}