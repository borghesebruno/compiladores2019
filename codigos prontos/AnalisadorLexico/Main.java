class Main {
  public static void main(String[] args) {
      AnalisadorLexico l;
      l = new AnalisadorLexico(new String("abcd +      ab123 ab@ ").toCharArray());
      System.out.println(l.nextToken());
      System.out.println(l.nextToken());
      System.out.println(l.nextToken());
      //System.out.println(l.nextToken());

  }
}