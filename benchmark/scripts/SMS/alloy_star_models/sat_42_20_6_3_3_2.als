
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41 extends Task {}
fact {
  frags =
    T0 -> T0_0 + T0 -> T0_1 +
    T1 -> T1_0 + T1 -> T1_1 + T1 -> T1_2 +
    T2 -> T2_0 + T2 -> T2_1 +
    T3 -> T3_0 +
    T4 -> T4_0 + T4 -> T4_1 + T4 -> T4_2 +
    T5 -> T5_0 + T5 -> T5_1 + T5 -> T5_2 +
    T6 -> T6_0 + T6 -> T6_1 +
    T7 -> T7_0 +
    T8 -> T8_0 + T8 -> T8_1 + T8 -> T8_2 +
    T9 -> T9_0 +
    T10 -> T10_0 + T10 -> T10_1 + T10 -> T10_2 +
    T11 -> T11_0 +
    T12 -> T12_0 + T12 -> T12_1 +
    T13 -> T13_0 +
    T14 -> T14_0 +
    T15 -> T15_0 +
    T16 -> T16_0 +
    T17 -> T17_0 + T17 -> T17_1 +
    T18 -> T18_0 + T18 -> T18_1 +
    T19 -> T19_0 + T19 -> T19_1 +
    T20 -> T20_0 +
    T21 -> T21_0 +
    T22 -> T22_0 + T22 -> T22_1 +
    T23 -> T23_0 + T23 -> T23_1 +
    T24 -> T24_0 +
    T25 -> T25_0 + T25 -> T25_1 +
    T26 -> T26_0 + T26 -> T26_1 +
    T27 -> T27_0 +
    T28 -> T28_0 + T28 -> T28_1 + T28 -> T28_2 +
    T29 -> T29_0 + T29 -> T29_1 + T29 -> T29_2 +
    T30 -> T30_0 + T30 -> T30_1 +
    T31 -> T31_0 +
    T32 -> T32_0 +
    T33 -> T33_0 +
    T34 -> T34_0 +
    T35 -> T35_0 +
    T36 -> T36_0 + T36 -> T36_1 +
    T37 -> T37_0 + T37 -> T37_1 + T37 -> T37_2 +
    T38 -> T38_0 +
    T39 -> T39_0 + T39 -> T39_1 + T39 -> T39_2 +
    T40 -> T40_0 +
    T41 -> T41_0 + T41 -> T41_1

  r = T0 -> 14 + T1 -> 7 + T2 -> 20 + T3 -> 1 + T4 -> 8 + T5 -> 11 + T6 -> 5 + T7 -> 17 + T8 -> 11 + T9 -> 18 + T10 -> 17 + T11 -> 10 + T12 -> 3 + T13 -> 10 + T14 -> 0 + T15 -> 9 + T16 -> 0 + T17 -> 0 + T18 -> 18 + T19 -> 14 + T20 -> 0 + T21 -> 5 + T22 -> 11 + T23 -> 12 + T24 -> 17 + T25 -> 14 + T26 -> 20 + T27 -> 5 + T28 -> 18 + T29 -> 18 + T30 -> 7 + T31 -> 5 + T32 -> 9 + T33 -> 18 + T34 -> 12 + T35 -> 0 + T36 -> 3 + T37 -> 8 + T38 -> 13 + T39 -> 6 + T40 -> 18 + T41 -> 13

  d = T0 -> 17 + T1 -> 17 + T2 -> 32 + T3 -> 3 + T4 -> 14 + T5 -> 17 + T6 -> 14 + T7 -> 29 + T8 -> 17 + T9 -> 28 + T10 -> 25 + T11 -> 28 + T12 -> 12 + T13 -> 16 + T14 -> 1 + T15 -> 11 + T16 -> 6 + T17 -> 8 + T18 -> 22 + T19 -> 20 + T20 -> 5 + T21 -> 7 + T22 -> 23 + T23 -> 16 + T24 -> 20 + T25 -> 18 + T26 -> 23 + T27 -> 7 + T28 -> 30 + T29 -> 23 + T30 -> 19 + T31 -> 8 + T32 -> 11 + T33 -> 21 + T34 -> 18 + T35 -> 12 + T36 -> 15 + T37 -> 20 + T38 -> 16 + T39 -> 12 + T40 -> 36 + T41 -> 17

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_0 + T37 -> T37_0 + T38 -> T38_0 + T39 -> T39_0 + T40 -> T40_0 + T41 -> T41_0

  final = T0 -> T0_1 + T1 -> T1_2 + T2 -> T2_1 + T3 -> T3_0 + T4 -> T4_2 + T5 -> T5_2 + T6 -> T6_1 + T7 -> T7_0 + T8 -> T8_2 + T9 -> T9_0 + T10 -> T10_2 + T11 -> T11_0 + T12 -> T12_1 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_1 + T18 -> T18_1 + T19 -> T19_1 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_1 + T23 -> T23_1 + T24 -> T24_0 + T25 -> T25_1 + T26 -> T26_1 + T27 -> T27_0 + T28 -> T28_2 + T29 -> T29_2 + T30 -> T30_1 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_1 + T37 -> T37_2 + T38 -> T38_0 + T39 -> T39_2 + T40 -> T40_0 + T41 -> T41_1

  deps =
    T36 -> T21 +
    T37 -> T14
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 36
}
one sig T0_0,T0_1 extends Frag {}
one sig T1_0,T1_1,T1_2 extends Frag {}
one sig T2_0,T2_1 extends Frag {}
one sig T3_0 extends Frag {}
one sig T4_0,T4_1,T4_2 extends Frag {}
one sig T5_0,T5_1,T5_2 extends Frag {}
one sig T6_0,T6_1 extends Frag {}
one sig T7_0 extends Frag {}
one sig T8_0,T8_1,T8_2 extends Frag {}
one sig T9_0 extends Frag {}
one sig T10_0,T10_1,T10_2 extends Frag {}
one sig T11_0 extends Frag {}
one sig T12_0,T12_1 extends Frag {}
one sig T13_0 extends Frag {}
one sig T14_0 extends Frag {}
one sig T15_0 extends Frag {}
one sig T16_0 extends Frag {}
one sig T17_0,T17_1 extends Frag {}
one sig T18_0,T18_1 extends Frag {}
one sig T19_0,T19_1 extends Frag {}
one sig T20_0 extends Frag {}
one sig T21_0 extends Frag {}
one sig T22_0,T22_1 extends Frag {}
one sig T23_0,T23_1 extends Frag {}
one sig T24_0 extends Frag {}
one sig T25_0,T25_1 extends Frag {}
one sig T26_0,T26_1 extends Frag {}
one sig T27_0 extends Frag {}
one sig T28_0,T28_1,T28_2 extends Frag {}
one sig T29_0,T29_1,T29_2 extends Frag {}
one sig T30_0,T30_1 extends Frag {}
one sig T31_0 extends Frag {}
one sig T32_0 extends Frag {}
one sig T33_0 extends Frag {}
one sig T34_0 extends Frag {}
one sig T35_0 extends Frag {}
one sig T36_0,T36_1 extends Frag {}
one sig T37_0,T37_1,T37_2 extends Frag {}
one sig T38_0 extends Frag {}
one sig T39_0,T39_1,T39_2 extends Frag {}
one sig T40_0 extends Frag {}
one sig T41_0,T41_1 extends Frag {}
fact {
  c =
    T0_0 -> 2 + T0_1 -> 1 +
    T1_0 -> 1 + T1_1 -> 1 + T1_2 -> 3 +
    T2_0 -> 2 + T2_1 -> 2 +
    T3_0 -> 1 +
    T4_0 -> 1 + T4_1 -> 3 + T4_2 -> 2 +
    T5_0 -> 3 + T5_1 -> 2 + T5_2 -> 1 +
    T6_0 -> 2 + T6_1 -> 1 +
    T7_0 -> 4 +
    T8_0 -> 1 + T8_1 -> 1 + T8_2 -> 1 +
    T9_0 -> 5 +
    T10_0 -> 1 + T10_1 -> 1 + T10_2 -> 2 +
    T11_0 -> 6 +
    T12_0 -> 1 + T12_1 -> 2 +
    T13_0 -> 2 +
    T14_0 -> 1 +
    T15_0 -> 1 +
    T16_0 -> 3 +
    T17_0 -> 1 + T17_1 -> 3 +
    T18_0 -> 1 + T18_1 -> 1 +
    T19_0 -> 2 + T19_1 -> 1 +
    T20_0 -> 5 +
    T21_0 -> 1 +
    T22_0 -> 4 + T22_1 -> 2 +
    T23_0 -> 1 + T23_1 -> 1 +
    T24_0 -> 1 +
    T25_0 -> 1 + T25_1 -> 1 +
    T26_0 -> 1 + T26_1 -> 2 +
    T27_0 -> 2 +
    T28_0 -> 3 + T28_1 -> 1 + T28_2 -> 2 +
    T29_0 -> 2 + T29_1 -> 1 + T29_2 -> 2 +
    T30_0 -> 1 + T30_1 -> 3 +
    T31_0 -> 3 +
    T32_0 -> 1 +
    T33_0 -> 1 +
    T34_0 -> 2 +
    T35_0 -> 4 +
    T36_0 -> 1 + T36_1 -> 5 +
    T37_0 -> 3 + T37_1 -> 1 + T37_2 -> 2 +
    T38_0 -> 1 +
    T39_0 -> 1 + T39_1 -> 1 + T39_2 -> 1 +
    T40_0 -> 6 +
    T41_0 -> 1 + T41_1 -> 1

  prev =
    T0_1 -> T0_0 +
    T1_1 -> T1_0 + T1_2 -> T1_1 +
    T2_1 -> T2_0 +
    T4_1 -> T4_0 + T4_2 -> T4_1 +
    T5_1 -> T5_0 + T5_2 -> T5_1 +
    T6_1 -> T6_0 +
    T8_1 -> T8_0 + T8_2 -> T8_1 +
    T10_1 -> T10_0 + T10_2 -> T10_1 +
    T12_1 -> T12_0 +
    T17_1 -> T17_0 +
    T18_1 -> T18_0 +
    T19_1 -> T19_0 +
    T22_1 -> T22_0 +
    T23_1 -> T23_0 +
    T25_1 -> T25_0 +
    T26_1 -> T26_0 +
    T28_1 -> T28_0 + T28_2 -> T28_1 +
    T29_1 -> T29_0 + T29_2 -> T29_1 +
    T30_1 -> T30_0 +
    T36_1 -> T36_0 +
    T37_1 -> T37_0 + T37_2 -> T37_1 +
    T39_1 -> T39_0 + T39_2 -> T39_1 +
    T41_1 -> T41_0
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
