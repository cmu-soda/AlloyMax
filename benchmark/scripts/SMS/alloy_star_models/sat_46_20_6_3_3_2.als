
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,T44,T45 extends Task {}
fact {
  frags =
    T0 -> T0_0 +
    T1 -> T1_0 + T1 -> T1_1 +
    T2 -> T2_0 + T2 -> T2_1 +
    T3 -> T3_0 +
    T4 -> T4_0 + T4 -> T4_1 +
    T5 -> T5_0 +
    T6 -> T6_0 +
    T7 -> T7_0 +
    T8 -> T8_0 + T8 -> T8_1 +
    T9 -> T9_0 +
    T10 -> T10_0 + T10 -> T10_1 +
    T11 -> T11_0 + T11 -> T11_1 + T11 -> T11_2 +
    T12 -> T12_0 +
    T13 -> T13_0 + T13 -> T13_1 +
    T14 -> T14_0 +
    T15 -> T15_0 +
    T16 -> T16_0 + T16 -> T16_1 +
    T17 -> T17_0 + T17 -> T17_1 +
    T18 -> T18_0 + T18 -> T18_1 + T18 -> T18_2 +
    T19 -> T19_0 + T19 -> T19_1 + T19 -> T19_2 +
    T20 -> T20_0 + T20 -> T20_1 +
    T21 -> T21_0 + T21 -> T21_1 + T21 -> T21_2 +
    T22 -> T22_0 + T22 -> T22_1 +
    T23 -> T23_0 +
    T24 -> T24_0 + T24 -> T24_1 +
    T25 -> T25_0 +
    T26 -> T26_0 +
    T27 -> T27_0 + T27 -> T27_1 + T27 -> T27_2 +
    T28 -> T28_0 + T28 -> T28_1 + T28 -> T28_2 +
    T29 -> T29_0 +
    T30 -> T30_0 + T30 -> T30_1 +
    T31 -> T31_0 + T31 -> T31_1 + T31 -> T31_2 +
    T32 -> T32_0 +
    T33 -> T33_0 +
    T34 -> T34_0 +
    T35 -> T35_0 + T35 -> T35_1 + T35 -> T35_2 +
    T36 -> T36_0 +
    T37 -> T37_0 +
    T38 -> T38_0 +
    T39 -> T39_0 + T39 -> T39_1 + T39 -> T39_2 +
    T40 -> T40_0 + T40 -> T40_1 + T40 -> T40_2 +
    T41 -> T41_0 + T41 -> T41_1 +
    T42 -> T42_0 +
    T43 -> T43_0 + T43 -> T43_1 + T43 -> T43_2 +
    T44 -> T44_0 +
    T45 -> T45_0 + T45 -> T45_1

  r = T0 -> 7 + T1 -> 0 + T2 -> 15 + T3 -> 13 + T4 -> 4 + T5 -> 13 + T6 -> 2 + T7 -> 10 + T8 -> 13 + T9 -> 8 + T10 -> 2 + T11 -> 17 + T12 -> 12 + T13 -> 13 + T14 -> 19 + T15 -> 10 + T16 -> 5 + T17 -> 5 + T18 -> 13 + T19 -> 10 + T20 -> 5 + T21 -> 19 + T22 -> 15 + T23 -> 16 + T24 -> 7 + T25 -> 9 + T26 -> 20 + T27 -> 15 + T28 -> 1 + T29 -> 20 + T30 -> 12 + T31 -> 9 + T32 -> 13 + T33 -> 7 + T34 -> 5 + T35 -> 10 + T36 -> 9 + T37 -> 2 + T38 -> 5 + T39 -> 11 + T40 -> 19 + T41 -> 12 + T42 -> 20 + T43 -> 20 + T44 -> 12 + T45 -> 12

  d = T0 -> 16 + T1 -> 12 + T2 -> 20 + T3 -> 17 + T4 -> 7 + T5 -> 15 + T6 -> 3 + T7 -> 19 + T8 -> 19 + T9 -> 12 + T10 -> 20 + T11 -> 23 + T12 -> 21 + T13 -> 23 + T14 -> 23 + T15 -> 16 + T16 -> 17 + T17 -> 15 + T18 -> 25 + T19 -> 18 + T20 -> 23 + T21 -> 22 + T22 -> 20 + T23 -> 31 + T24 -> 11 + T25 -> 12 + T26 -> 21 + T27 -> 21 + T28 -> 19 + T29 -> 21 + T30 -> 14 + T31 -> 19 + T32 -> 16 + T33 -> 9 + T34 -> 6 + T35 -> 18 + T36 -> 27 + T37 -> 4 + T38 -> 11 + T39 -> 20 + T40 -> 31 + T41 -> 14 + T42 -> 26 + T43 -> 23 + T44 -> 16 + T45 -> 21

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_0 + T37 -> T37_0 + T38 -> T38_0 + T39 -> T39_0 + T40 -> T40_0 + T41 -> T41_0 + T42 -> T42_0 + T43 -> T43_0 + T44 -> T44_0 + T45 -> T45_0

  final = T0 -> T0_0 + T1 -> T1_1 + T2 -> T2_1 + T3 -> T3_0 + T4 -> T4_1 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_1 + T9 -> T9_0 + T10 -> T10_1 + T11 -> T11_2 + T12 -> T12_0 + T13 -> T13_1 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_1 + T17 -> T17_1 + T18 -> T18_2 + T19 -> T19_2 + T20 -> T20_1 + T21 -> T21_2 + T22 -> T22_1 + T23 -> T23_0 + T24 -> T24_1 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_2 + T28 -> T28_2 + T29 -> T29_0 + T30 -> T30_1 + T31 -> T31_2 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_2 + T36 -> T36_0 + T37 -> T37_0 + T38 -> T38_0 + T39 -> T39_2 + T40 -> T40_2 + T41 -> T41_1 + T42 -> T42_0 + T43 -> T43_2 + T44 -> T44_0 + T45 -> T45_1

  deps =
    T7 -> T10 +
    T11 -> T18
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 31
}
one sig T0_0 extends Frag {}
one sig T1_0,T1_1 extends Frag {}
one sig T2_0,T2_1 extends Frag {}
one sig T3_0 extends Frag {}
one sig T4_0,T4_1 extends Frag {}
one sig T5_0 extends Frag {}
one sig T6_0 extends Frag {}
one sig T7_0 extends Frag {}
one sig T8_0,T8_1 extends Frag {}
one sig T9_0 extends Frag {}
one sig T10_0,T10_1 extends Frag {}
one sig T11_0,T11_1,T11_2 extends Frag {}
one sig T12_0 extends Frag {}
one sig T13_0,T13_1 extends Frag {}
one sig T14_0 extends Frag {}
one sig T15_0 extends Frag {}
one sig T16_0,T16_1 extends Frag {}
one sig T17_0,T17_1 extends Frag {}
one sig T18_0,T18_1,T18_2 extends Frag {}
one sig T19_0,T19_1,T19_2 extends Frag {}
one sig T20_0,T20_1 extends Frag {}
one sig T21_0,T21_1,T21_2 extends Frag {}
one sig T22_0,T22_1 extends Frag {}
one sig T23_0 extends Frag {}
one sig T24_0,T24_1 extends Frag {}
one sig T25_0 extends Frag {}
one sig T26_0 extends Frag {}
one sig T27_0,T27_1,T27_2 extends Frag {}
one sig T28_0,T28_1,T28_2 extends Frag {}
one sig T29_0 extends Frag {}
one sig T30_0,T30_1 extends Frag {}
one sig T31_0,T31_1,T31_2 extends Frag {}
one sig T32_0 extends Frag {}
one sig T33_0 extends Frag {}
one sig T34_0 extends Frag {}
one sig T35_0,T35_1,T35_2 extends Frag {}
one sig T36_0 extends Frag {}
one sig T37_0 extends Frag {}
one sig T38_0 extends Frag {}
one sig T39_0,T39_1,T39_2 extends Frag {}
one sig T40_0,T40_1,T40_2 extends Frag {}
one sig T41_0,T41_1 extends Frag {}
one sig T42_0 extends Frag {}
one sig T43_0,T43_1,T43_2 extends Frag {}
one sig T44_0 extends Frag {}
one sig T45_0,T45_1 extends Frag {}
fact {
  c =
    T0_0 -> 3 +
    T1_0 -> 2 + T1_1 -> 2 +
    T2_0 -> 3 + T2_1 -> 2 +
    T3_0 -> 4 +
    T4_0 -> 2 + T4_1 -> 1 +
    T5_0 -> 1 +
    T6_0 -> 1 +
    T7_0 -> 3 +
    T8_0 -> 1 + T8_1 -> 1 +
    T9_0 -> 2 +
    T10_0 -> 3 + T10_1 -> 3 +
    T11_0 -> 1 + T11_1 -> 1 + T11_2 -> 1 +
    T12_0 -> 3 +
    T13_0 -> 3 + T13_1 -> 2 +
    T14_0 -> 4 +
    T15_0 -> 2 +
    T16_0 -> 2 + T16_1 -> 2 +
    T17_0 -> 2 + T17_1 -> 3 +
    T18_0 -> 4 + T18_1 -> 1 + T18_2 -> 1 +
    T19_0 -> 1 + T19_1 -> 1 + T19_2 -> 2 +
    T20_0 -> 3 + T20_1 -> 3 +
    T21_0 -> 1 + T21_1 -> 1 + T21_2 -> 1 +
    T22_0 -> 2 + T22_1 -> 3 +
    T23_0 -> 5 +
    T24_0 -> 1 + T24_1 -> 1 +
    T25_0 -> 1 +
    T26_0 -> 1 +
    T27_0 -> 1 + T27_1 -> 2 + T27_2 -> 3 +
    T28_0 -> 2 + T28_1 -> 2 + T28_2 -> 2 +
    T29_0 -> 1 +
    T30_0 -> 1 + T30_1 -> 1 +
    T31_0 -> 2 + T31_1 -> 2 + T31_2 -> 1 +
    T32_0 -> 1 +
    T33_0 -> 1 +
    T34_0 -> 1 +
    T35_0 -> 1 + T35_1 -> 1 + T35_2 -> 2 +
    T36_0 -> 6 +
    T37_0 -> 2 +
    T38_0 -> 2 +
    T39_0 -> 1 + T39_1 -> 1 + T39_2 -> 1 +
    T40_0 -> 1 + T40_1 -> 2 + T40_2 -> 1 +
    T41_0 -> 1 + T41_1 -> 1 +
    T42_0 -> 2 +
    T43_0 -> 1 + T43_1 -> 1 + T43_2 -> 1 +
    T44_0 -> 4 +
    T45_0 -> 2 + T45_1 -> 1

  prev =
    T1_1 -> T1_0 +
    T2_1 -> T2_0 +
    T4_1 -> T4_0 +
    T8_1 -> T8_0 +
    T10_1 -> T10_0 +
    T11_1 -> T11_0 + T11_2 -> T11_1 +
    T13_1 -> T13_0 +
    T16_1 -> T16_0 +
    T17_1 -> T17_0 +
    T18_1 -> T18_0 + T18_2 -> T18_1 +
    T19_1 -> T19_0 + T19_2 -> T19_1 +
    T20_1 -> T20_0 +
    T21_1 -> T21_0 + T21_2 -> T21_1 +
    T22_1 -> T22_0 +
    T24_1 -> T24_0 +
    T27_1 -> T27_0 + T27_2 -> T27_1 +
    T28_1 -> T28_0 + T28_2 -> T28_1 +
    T30_1 -> T30_0 +
    T31_1 -> T31_0 + T31_2 -> T31_1 +
    T35_1 -> T35_0 + T35_2 -> T35_1 +
    T39_1 -> T39_0 + T39_2 -> T39_1 +
    T40_1 -> T40_0 + T40_2 -> T40_1 +
    T41_1 -> T41_0 +
    T43_1 -> T43_0 + T43_2 -> T43_1 +
    T45_1 -> T45_0
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
  all f: Frag | f.start < 31
}

run {
  valid[Completed, s]
  all completed': set Task, s': Frag -> Int |
    valid[completed', s'] implies #Completed >= #completed'
} for 7 Int
