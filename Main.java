class Main {
    public static void main(String[] args) {
        AnalisadorLexico l;
        l = new AnalisadorLexico("prog.in");
        while(!l.eof()) {
            System.out.println(l.nextToken());
        }
    }
}