public class factR {
int factR (int x) {
  if (x == 1) {
    return 1;
  } else {
    return x * factR (x - 1);
  }
}
}
