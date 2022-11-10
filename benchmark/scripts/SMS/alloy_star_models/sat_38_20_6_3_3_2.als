
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37 extends Task {}
fact {
  frags =
    T0 -> T0_0 +
    T1 -> T1_0 + T1 -> T1_1 + T1 -> T1_2 +
    T2 -> T2_0 + T2 -> T2_1 +
    T3 -> T3_0 +
    T4 -> T4_0 + T4 -> T4_1 + T4 -> T4_2 +
    T5 -> T5_0 +
    T6 -> T6_0 + T6 -> T6_1 +
    T7 -> T7_0 + T7 -> T7_1 +
    T8 -> T8_0 +
    T9 -> T9_0 + T9 -> T9_1 +
    T10 -> T10_0 +
    T11 -> T11_0 + T11 -> T11_1 + T11 -> T11_2 +
    T12 -> T12_0 + T12 -> T12_1 + T12 -> T12_2 +
    T13 -> T13_0 + T13 -> T13_1 +
    T14 -> T14_0 + T14 -> T14_1 +
    T15 -> T15_0 +
    T16 -> T16_0 + T16 -> T16_1 + T16 -> T16_2 +
    T17 -> T17_0 +
    T18 -> T18_0 +
    T19 -> T19_0 +
    T20 -> T20_0 +
    T21 -> T21_0 + T21 -> T21_1 + T21 -> T21_2 +
    T22 -> T22_0 + T22 -> T22_1 +
    T23 -> T23_0 + T23 -> T23_1 +
    T24 -> T24_0 +
    T25 -> T25_0 +
    T26 -> T26_0 + T26 -> T26_1 +
    T27 -> T27_0 + T27 -> T27_1 +
    T28 -> T28_0 +
    T29 -> T29_0 + T29 -> T29_1 +
    T30 -> T30_0 + T30 -> T30_1 +
    T31 -> T31_0 +
    T32 -> T32_0 + T32 -> T32_1 +
    T33 -> T33_0 +
    T34 -> T34_0 + T34 -> T34_1 +
    T35 -> T35_0 + T35 -> T35_1 + T35 -> T35_2 +
    T36 -> T36_0 +
    T37 -> T37_0 + T37 -> T37_1 + T37 -> T37_2

  r = T0 -> 2 + T1 -> 11 + T2 -> 16 + T3 -> 11 + T4 -> 16 + T5 -> 19 + T6 -> 1 + T7 -> 12 + T8 -> 6 + T9 -> 9 + T10 -> 16 + T11 -> 7 + T12 -> 8 + T13 -> 19 + T14 -> 4 + T15 -> 19 + T16 -> 3 + T17 -> 9 + T18 -> 11 + T19 -> 18 + T20 -> 0 + T21 -> 14 + T22 -> 1 + T23 -> 1 + T24 -> 20 + T25 -> 12 + T26 -> 10 + T27 -> 13 + T28 -> 17 + T29 -> 8 + T30 -> 4 + T31 -> 18 + T32 -> 14 + T33 -> 10 + T34 -> 3 + T35 -> 6 + T36 -> 8 + T37 -> 20

  d = T0 -> 17 + T1 -> 19 + T2 -> 22 + T3 -> 26 + T4 -> 19 + T5 -> 29 + T6 -> 3 + T7 -> 15 + T8 -> 8 + T9 -> 12 + T10 -> 26 + T11 -> 17 + T12 -> 18 + T13 -> 23 + T14 -> 10 + T15 -> 29 + T16 -> 12 + T17 -> 11 + T18 -> 23 + T19 -> 21 + T20 -> 5 + T21 -> 26 + T22 -> 4 + T23 -> 7 + T24 -> 23 + T25 -> 15 + T26 -> 25 + T27 -> 23 + T28 -> 18 + T29 -> 14 + T30 -> 10 + T31 -> 21 + T32 -> 22 + T33 -> 18 + T34 -> 13 + T35 -> 12 + T36 -> 10 + T37 -> 35

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_0 + T37 -> T37_0

  final = T0 -> T0_0 + T1 -> T1_2 + T2 -> T2_1 + T3 -> T3_0 + T4 -> T4_2 + T5 -> T5_0 + T6 -> T6_1 + T7 -> T7_1 + T8 -> T8_0 + T9 -> T9_1 + T10 -> T10_0 + T11 -> T11_2 + T12 -> T12_2 + T13 -> T13_1 + T14 -> T14_1 + T15 -> T15_0 + T16 -> T16_2 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_2 + T22 -> T22_1 + T23 -> T23_1 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_1 + T27 -> T27_1 + T28 -> T28_0 + T29 -> T29_1 + T30 -> T30_1 + T31 -> T31_0 + T32 -> T32_1 + T33 -> T33_0 + T34 -> T34_1 + T35 -> T35_2 + T36 -> T36_0 + T37 -> T37_2

  deps =
    T18 -> T34 +
    T33 -> T25
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 35
}
one sig T0_0 extends Frag {}
one sig T1_0,T1_1,T1_2 extends Frag {}
one sig T2_0,T2_1 extends Frag {}
one sig T3_0 extends Frag {}
one sig T4_0,T4_1,T4_2 extends Frag {}
one sig T5_0 extends Frag {}
one sig T6_0,T6_1 extends Frag {}
one sig T7_0,T7_1 extends Frag {}
one sig T8_0 extends Frag {}
one sig T9_0,T9_1 extends Frag {}
one sig T10_0 extends Frag {}
one sig T11_0,T11_1,T11_2 extends Frag {}
one sig T12_0,T12_1,T12_2 extends Frag {}
one sig T13_0,T13_1 extends Frag {}
one sig T14_0,T14_1 extends Frag {}
one sig T15_0 extends Frag {}
one sig T16_0,T16_1,T16_2 extends Frag {}
one sig T17_0 extends Frag {}
one sig T18_0 extends Frag {}
one sig T19_0 extends Frag {}
one sig T20_0 extends Frag {}
one sig T21_0,T21_1,T21_2 extends Frag {}
one sig T22_0,T22_1 extends Frag {}
one sig T23_0,T23_1 extends Frag {}
one sig T24_0 extends Frag {}
one sig T25_0 extends Frag {}
one sig T26_0,T26_1 extends Frag {}
one sig T27_0,T27_1 extends Frag {}
one sig T28_0 extends Frag {}
one sig T29_0,T29_1 extends Frag {}
one sig T30_0,T30_1 extends Frag {}
one sig T31_0 extends Frag {}
one sig T32_0,T32_1 extends Frag {}
one sig T33_0 extends Frag {}
one sig T34_0,T34_1 extends Frag {}
one sig T35_0,T35_1,T35_2 extends Frag {}
one sig T36_0 extends Frag {}
one sig T37_0,T37_1,T37_2 extends Frag {}
fact {
  c =
    T0_0 -> 5 +
    T1_0 -> 1 + T1_1 -> 1 + T1_2 -> 2 +
    T2_0 -> 1 + T2_1 -> 1 +
    T3_0 -> 5 +
    T4_0 -> 1 + T4_1 -> 1 + T4_2 -> 1 +
    T5_0 -> 5 +
    T6_0 -> 1 + T6_1 -> 1 +
    T7_0 -> 1 + T7_1 -> 2 +
    T8_0 -> 2 +
    T9_0 -> 1 + T9_1 -> 2 +
    T10_0 -> 5 +
    T11_0 -> 2 + T11_1 -> 1 + T11_2 -> 2 +
    T12_0 -> 1 + T12_1 -> 1 + T12_2 -> 3 +
    T13_0 -> 1 + T13_1 -> 1 +
    T14_0 -> 1 + T14_1 -> 5 +
    T15_0 -> 5 +
    T16_0 -> 1 + T16_1 -> 1 + T16_2 -> 1 +
    T17_0 -> 1 +
    T18_0 -> 6 +
    T19_0 -> 1 +
    T20_0 -> 5 +
    T21_0 -> 2 + T21_1 -> 1 + T21_2 -> 1 +
    T22_0 -> 1 + T22_1 -> 2 +
    T23_0 -> 1 + T23_1 -> 1 +
    T24_0 -> 1 +
    T25_0 -> 1 +
    T26_0 -> 3 + T26_1 -> 2 +
    T27_0 -> 2 + T27_1 -> 3 +
    T28_0 -> 1 +
    T29_0 -> 1 + T29_1 -> 1 +
    T30_0 -> 1 + T30_1 -> 1 +
    T31_0 -> 1 +
    T32_0 -> 2 + T32_1 -> 2 +
    T33_0 -> 4 +
    T34_0 -> 3 + T34_1 -> 2 +
    T35_0 -> 1 + T35_1 -> 3 + T35_2 -> 2 +
    T36_0 -> 2 +
    T37_0 -> 1 + T37_1 -> 3 + T37_2 -> 1

  prev =
    T1_1 -> T1_0 + T1_2 -> T1_1 +
    T2_1 -> T2_0 +
    T4_1 -> T4_0 + T4_2 -> T4_1 +
    T6_1 -> T6_0 +
    T7_1 -> T7_0 +
    T9_1 -> T9_0 +
    T11_1 -> T11_0 + T11_2 -> T11_1 +
    T12_1 -> T12_0 + T12_2 -> T12_1 +
    T13_1 -> T13_0 +
    T14_1 -> T14_0 +
    T16_1 -> T16_0 + T16_2 -> T16_1 +
    T21_1 -> T21_0 + T21_2 -> T21_1 +
    T22_1 -> T22_0 +
    T23_1 -> T23_0 +
    T26_1 -> T26_0 +
    T27_1 -> T27_0 +
    T29_1 -> T29_0 +
    T30_1 -> T30_0 +
    T32_1 -> T32_0 +
    T34_1 -> T34_0 +
    T35_1 -> T35_0 + T35_2 -> T35_1 +
    T37_1 -> T37_0 + T37_2 -> T37_1
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
  all f: Frag | f.start < 35
}

run {
  valid[Completed, s]
  all completed': set Task, s': Frag -> Int |
    valid[completed', s'] implies #Completed >= #completed'
} for 7 Int
