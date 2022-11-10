
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33 extends Task {}
fact {
  frags =
    T0 -> T0_0 + T0 -> T0_1 +
    T1 -> T1_0 +
    T2 -> T2_0 +
    T3 -> T3_0 +
    T4 -> T4_0 +
    T5 -> T5_0 +
    T6 -> T6_0 + T6 -> T6_1 +
    T7 -> T7_0 + T7 -> T7_1 + T7 -> T7_2 +
    T8 -> T8_0 + T8 -> T8_1 + T8 -> T8_2 +
    T9 -> T9_0 + T9 -> T9_1 +
    T10 -> T10_0 +
    T11 -> T11_0 + T11 -> T11_1 +
    T12 -> T12_0 +
    T13 -> T13_0 + T13 -> T13_1 +
    T14 -> T14_0 + T14 -> T14_1 +
    T15 -> T15_0 +
    T16 -> T16_0 +
    T17 -> T17_0 +
    T18 -> T18_0 + T18 -> T18_1 + T18 -> T18_2 +
    T19 -> T19_0 + T19 -> T19_1 +
    T20 -> T20_0 +
    T21 -> T21_0 +
    T22 -> T22_0 + T22 -> T22_1 +
    T23 -> T23_0 + T23 -> T23_1 + T23 -> T23_2 +
    T24 -> T24_0 + T24 -> T24_1 + T24 -> T24_2 +
    T25 -> T25_0 +
    T26 -> T26_0 + T26 -> T26_1 + T26 -> T26_2 +
    T27 -> T27_0 + T27 -> T27_1 + T27 -> T27_2 +
    T28 -> T28_0 +
    T29 -> T29_0 +
    T30 -> T30_0 +
    T31 -> T31_0 + T31 -> T31_1 + T31 -> T31_2 +
    T32 -> T32_0 +
    T33 -> T33_0 + T33 -> T33_1 + T33 -> T33_2

  r = T0 -> 6 + T1 -> 3 + T2 -> 7 + T3 -> 8 + T4 -> 17 + T5 -> 4 + T6 -> 9 + T7 -> 18 + T8 -> 17 + T9 -> 16 + T10 -> 17 + T11 -> 12 + T12 -> 0 + T13 -> 1 + T14 -> 18 + T15 -> 18 + T16 -> 18 + T17 -> 2 + T18 -> 5 + T19 -> 5 + T20 -> 3 + T21 -> 2 + T22 -> 11 + T23 -> 15 + T24 -> 9 + T25 -> 18 + T26 -> 14 + T27 -> 18 + T28 -> 1 + T29 -> 1 + T30 -> 18 + T31 -> 6 + T32 -> 5 + T33 -> 8

  d = T0 -> 8 + T1 -> 4 + T2 -> 8 + T3 -> 23 + T4 -> 21 + T5 -> 13 + T6 -> 18 + T7 -> 36 + T8 -> 27 + T9 -> 28 + T10 -> 32 + T11 -> 18 + T12 -> 2 + T13 -> 7 + T14 -> 21 + T15 -> 33 + T16 -> 20 + T17 -> 5 + T18 -> 23 + T19 -> 10 + T20 -> 18 + T21 -> 4 + T22 -> 17 + T23 -> 33 + T24 -> 24 + T25 -> 30 + T26 -> 32 + T27 -> 26 + T28 -> 3 + T29 -> 4 + T30 -> 22 + T31 -> 18 + T32 -> 6 + T33 -> 11

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0

  final = T0 -> T0_1 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_1 + T7 -> T7_2 + T8 -> T8_2 + T9 -> T9_1 + T10 -> T10_0 + T11 -> T11_1 + T12 -> T12_0 + T13 -> T13_1 + T14 -> T14_1 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_2 + T19 -> T19_1 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_1 + T23 -> T23_2 + T24 -> T24_2 + T25 -> T25_0 + T26 -> T26_2 + T27 -> T27_2 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_2 + T32 -> T32_0 + T33 -> T33_2

  deps =
    T8 -> T24 +
    T32 -> T13
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 36
}
one sig T0_0,T0_1 extends Frag {}
one sig T1_0 extends Frag {}
one sig T2_0 extends Frag {}
one sig T3_0 extends Frag {}
one sig T4_0 extends Frag {}
one sig T5_0 extends Frag {}
one sig T6_0,T6_1 extends Frag {}
one sig T7_0,T7_1,T7_2 extends Frag {}
one sig T8_0,T8_1,T8_2 extends Frag {}
one sig T9_0,T9_1 extends Frag {}
one sig T10_0 extends Frag {}
one sig T11_0,T11_1 extends Frag {}
one sig T12_0 extends Frag {}
one sig T13_0,T13_1 extends Frag {}
one sig T14_0,T14_1 extends Frag {}
one sig T15_0 extends Frag {}
one sig T16_0 extends Frag {}
one sig T17_0 extends Frag {}
one sig T18_0,T18_1,T18_2 extends Frag {}
one sig T19_0,T19_1 extends Frag {}
one sig T20_0 extends Frag {}
one sig T21_0 extends Frag {}
one sig T22_0,T22_1 extends Frag {}
one sig T23_0,T23_1,T23_2 extends Frag {}
one sig T24_0,T24_1,T24_2 extends Frag {}
one sig T25_0 extends Frag {}
one sig T26_0,T26_1,T26_2 extends Frag {}
one sig T27_0,T27_1,T27_2 extends Frag {}
one sig T28_0 extends Frag {}
one sig T29_0 extends Frag {}
one sig T30_0 extends Frag {}
one sig T31_0,T31_1,T31_2 extends Frag {}
one sig T32_0 extends Frag {}
one sig T33_0,T33_1,T33_2 extends Frag {}
fact {
  c =
    T0_0 -> 1 + T0_1 -> 1 +
    T1_0 -> 1 +
    T2_0 -> 1 +
    T3_0 -> 5 +
    T4_0 -> 2 +
    T5_0 -> 3 +
    T6_0 -> 2 + T6_1 -> 1 +
    T7_0 -> 3 + T7_1 -> 2 + T7_2 -> 1 +
    T8_0 -> 3 + T8_1 -> 1 + T8_2 -> 1 +
    T9_0 -> 4 + T9_1 -> 2 +
    T10_0 -> 5 +
    T11_0 -> 3 + T11_1 -> 3 +
    T12_0 -> 1 +
    T13_0 -> 1 + T13_1 -> 2 +
    T14_0 -> 1 + T14_1 -> 2 +
    T15_0 -> 5 +
    T16_0 -> 1 +
    T17_0 -> 3 +
    T18_0 -> 2 + T18_1 -> 3 + T18_2 -> 1 +
    T19_0 -> 2 + T19_1 -> 3 +
    T20_0 -> 5 +
    T21_0 -> 1 +
    T22_0 -> 2 + T22_1 -> 4 +
    T23_0 -> 3 + T23_1 -> 2 + T23_2 -> 1 +
    T24_0 -> 3 + T24_1 -> 1 + T24_2 -> 1 +
    T25_0 -> 6 +
    T26_0 -> 2 + T26_1 -> 1 + T26_2 -> 3 +
    T27_0 -> 1 + T27_1 -> 1 + T27_2 -> 2 +
    T28_0 -> 1 +
    T29_0 -> 3 +
    T30_0 -> 4 +
    T31_0 -> 3 + T31_1 -> 1 + T31_2 -> 2 +
    T32_0 -> 1 +
    T33_0 -> 1 + T33_1 -> 1 + T33_2 -> 1

  prev =
    T0_1 -> T0_0 +
    T6_1 -> T6_0 +
    T7_1 -> T7_0 + T7_2 -> T7_1 +
    T8_1 -> T8_0 + T8_2 -> T8_1 +
    T9_1 -> T9_0 +
    T11_1 -> T11_0 +
    T13_1 -> T13_0 +
    T14_1 -> T14_0 +
    T18_1 -> T18_0 + T18_2 -> T18_1 +
    T19_1 -> T19_0 +
    T22_1 -> T22_0 +
    T23_1 -> T23_0 + T23_2 -> T23_1 +
    T24_1 -> T24_0 + T24_2 -> T24_1 +
    T26_1 -> T26_0 + T26_2 -> T26_1 +
    T27_1 -> T27_0 + T27_2 -> T27_1 +
    T31_1 -> T31_0 + T31_2 -> T31_1 +
    T33_1 -> T33_0 + T33_2 -> T33_1
}

pred StartAfterRelease[completed: set Task, start: Frag -> Int] {
  all t: completed | t.first.start >= t.r
}

pred StartAfterPrevFrag[completed: set Task, start: Frag -> Int] {
  all t: completed, f1, f2: t.frags | f1 -> f2 in prev implies
    f1.start >= plus[f2.start, f2.c]
}

pred SingleFrag[completed: set Task, start: Frag -> Int] {
  all disj t1, t2: completed, f1: t1.frags, f2: t2.frags |
    f2.start >= plus[f1.start, f1.c] or f1.start >= plus[f2.start, f2.c]
}

pred TaskDep[completed: set Task, start: Frag -> Int] {
  all t1: completed, t2: t1.deps {
    t1.first.start >= plus[t2.final.start, t2.final.c]
    t2 in completed
  }
}

pred Deadline[completed: set Task, start: Frag -> Int] {
  all t: completed | t.d >= plus[t.final.start, t.final.c]
}

pred valid[completed: set Task, start: Frag -> Int] {
  StartAfterRelease[completed, start]
  StartAfterPrevFrag[completed, start]
  SingleFrag[completed, start]
  TaskDep[completed, start]
  Deadline[completed, start]
  all f: Frag | f.start < 36
}

run {
  valid[Completed, s]
  all completed': set Task, s': Frag -> Int |
    valid[completed', s'] implies #Completed >= #completed'
} for 7 Int
