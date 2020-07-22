public class fact{
int fact (int x) {
  int result = 1;
  while (x >= 1) {
    result = result * x;
    x = x - 1;
  }
  return result;
}
}
