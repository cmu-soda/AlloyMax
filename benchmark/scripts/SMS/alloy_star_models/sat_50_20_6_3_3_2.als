
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,T44,T45,T46,T47,T48,T49 extends Task {}
fact {
  frags =
    T0 -> T0_0 + T0 -> T0_1 +
    T1 -> T1_0 +
    T2 -> T2_0 + T2 -> T2_1 + T2 -> T2_2 +
    T3 -> T3_0 + T3 -> T3_1 +
    T4 -> T4_0 + T4 -> T4_1 +
    T5 -> T5_0 +
    T6 -> T6_0 +
    T7 -> T7_0 + T7 -> T7_1 +
    T8 -> T8_0 + T8 -> T8_1 +
    T9 -> T9_0 +
    T10 -> T10_0 +
    T11 -> T11_0 +
    T12 -> T12_0 + T12 -> T12_1 +
    T13 -> T13_0 + T13 -> T13_1 + T13 -> T13_2 +
    T14 -> T14_0 + T14 -> T14_1 +
    T15 -> T15_0 + T15 -> T15_1 + T15 -> T15_2 +
    T16 -> T16_0 + T16 -> T16_1 +
    T17 -> T17_0 +
    T18 -> T18_0 +
    T19 -> T19_0 + T19 -> T19_1 + T19 -> T19_2 +
    T20 -> T20_0 +
    T21 -> T21_0 +
    T22 -> T22_0 +
    T23 -> T23_0 +
    T24 -> T24_0 + T24 -> T24_1 +
    T25 -> T25_0 + T25 -> T25_1 +
    T26 -> T26_0 + T26 -> T26_1 +
    T27 -> T27_0 +
    T28 -> T28_0 + T28 -> T28_1 + T28 -> T28_2 +
    T29 -> T29_0 +
    T30 -> T30_0 + T30 -> T30_1 +
    T31 -> T31_0 +
    T32 -> T32_0 + T32 -> T32_1 + T32 -> T32_2 +
    T33 -> T33_0 + T33 -> T33_1 +
    T34 -> T34_0 +
    T35 -> T35_0 +
    T36 -> T36_0 + T36 -> T36_1 + T36 -> T36_2 +
    T37 -> T37_0 + T37 -> T37_1 + T37 -> T37_2 +
    T38 -> T38_0 + T38 -> T38_1 + T38 -> T38_2 +
    T39 -> T39_0 + T39 -> T39_1 + T39 -> T39_2 +
    T40 -> T40_0 + T40 -> T40_1 +
    T41 -> T41_0 + T41 -> T41_1 + T41 -> T41_2 +
    T42 -> T42_0 +
    T43 -> T43_0 + T43 -> T43_1 +
    T44 -> T44_0 + T44 -> T44_1 +
    T45 -> T45_0 + T45 -> T45_1 +
    T46 -> T46_0 + T46 -> T46_1 +
    T47 -> T47_0 + T47 -> T47_1 +
    T48 -> T48_0 +
    T49 -> T49_0

  r = T0 -> 5 + T1 -> 16 + T2 -> 10 + T3 -> 2 + T4 -> 14 + T5 -> 9 + T6 -> 11 + T7 -> 13 + T8 -> 10 + T9 -> 0 + T10 -> 15 + T11 -> 8 + T12 -> 0 + T13 -> 0 + T14 -> 15 + T15 -> 1 + T16 -> 20 + T17 -> 20 + T18 -> 7 + T19 -> 0 + T20 -> 0 + T21 -> 10 + T22 -> 1 + T23 -> 11 + T24 -> 0 + T25 -> 3 + T26 -> 9 + T27 -> 14 + T28 -> 19 + T29 -> 17 + T30 -> 17 + T31 -> 8 + T32 -> 8 + T33 -> 11 + T34 -> 10 + T35 -> 2 + T36 -> 14 + T37 -> 14 + T38 -> 12 + T39 -> 0 + T40 -> 12 + T41 -> 10 + T42 -> 10 + T43 -> 16 + T44 -> 7 + T45 -> 20 + T46 -> 11 + T47 -> 7 + T48 -> 2 + T49 -> 7

  d = T0 -> 17 + T1 -> 24 + T2 -> 14 + T3 -> 12 + T4 -> 23 + T5 -> 17 + T6 -> 14 + T7 -> 17 + T8 -> 14 + T9 -> 5 + T10 -> 33 + T11 -> 10 + T12 -> 6 + T13 -> 18 + T14 -> 18 + T15 -> 13 + T16 -> 35 + T17 -> 35 + T18 -> 10 + T19 -> 8 + T20 -> 6 + T21 -> 11 + T22 -> 7 + T23 -> 15 + T24 -> 4 + T25 -> 18 + T26 -> 15 + T27 -> 32 + T28 -> 23 + T29 -> 21 + T30 -> 19 + T31 -> 11 + T32 -> 13 + T33 -> 21 + T34 -> 28 + T35 -> 3 + T36 -> 26 + T37 -> 22 + T38 -> 15 + T39 -> 9 + T40 -> 18 + T41 -> 18 + T42 -> 13 + T43 -> 19 + T44 -> 15 + T45 -> 28 + T46 -> 14 + T47 -> 19 + T48 -> 5 + T49 -> 10

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_0 + T37 -> T37_0 + T38 -> T38_0 + T39 -> T39_0 + T40 -> T40_0 + T41 -> T41_0 + T42 -> T42_0 + T43 -> T43_0 + T44 -> T44_0 + T45 -> T45_0 + T46 -> T46_0 + T47 -> T47_0 + T48 -> T48_0 + T49 -> T49_0

  final = T0 -> T0_1 + T1 -> T1_0 + T2 -> T2_2 + T3 -> T3_1 + T4 -> T4_1 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_1 + T8 -> T8_1 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_1 + T13 -> T13_2 + T14 -> T14_1 + T15 -> T15_2 + T16 -> T16_1 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_2 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_1 + T25 -> T25_1 + T26 -> T26_1 + T27 -> T27_0 + T28 -> T28_2 + T29 -> T29_0 + T30 -> T30_1 + T31 -> T31_0 + T32 -> T32_2 + T33 -> T33_1 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_2 + T37 -> T37_2 + T38 -> T38_2 + T39 -> T39_2 + T40 -> T40_1 + T41 -> T41_2 + T42 -> T42_0 + T43 -> T43_1 + T44 -> T44_1 + T45 -> T45_1 + T46 -> T46_1 + T47 -> T47_1 + T48 -> T48_0 + T49 -> T49_0

  deps =
    T6 -> T44 +
    T27 -> T5
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 35
}
one sig T0_0,T0_1 extends Frag {}
one sig T1_0 extends Frag {}
one sig T2_0,T2_1,T2_2 extends Frag {}
one sig T3_0,T3_1 extends Frag {}
one sig T4_0,T4_1 extends Frag {}
one sig T5_0 extends Frag {}
one sig T6_0 extends Frag {}
one sig T7_0,T7_1 extends Frag {}
one sig T8_0,T8_1 extends Frag {}
one sig T9_0 extends Frag {}
one sig T10_0 extends Frag {}
one sig T11_0 extends Frag {}
one sig T12_0,T12_1 extends Frag {}
one sig T13_0,T13_1,T13_2 extends Frag {}
one sig T14_0,T14_1 extends Frag {}
one sig T15_0,T15_1,T15_2 extends Frag {}
one sig T16_0,T16_1 extends Frag {}
one sig T17_0 extends Frag {}
one sig T18_0 extends Frag {}
one sig T19_0,T19_1,T19_2 extends Frag {}
one sig T20_0 extends Frag {}
one sig T21_0 extends Frag {}
one sig T22_0 extends Frag {}
one sig T23_0 extends Frag {}
one sig T24_0,T24_1 extends Frag {}
one sig T25_0,T25_1 extends Frag {}
one sig T26_0,T26_1 extends Frag {}
one sig T27_0 extends Frag {}
one sig T28_0,T28_1,T28_2 extends Frag {}
one sig T29_0 extends Frag {}
one sig T30_0,T30_1 extends Frag {}
one sig T31_0 extends Frag {}
one sig T32_0,T32_1,T32_2 extends Frag {}
one sig T33_0,T33_1 extends Frag {}
one sig T34_0 extends Frag {}
one sig T35_0 extends Frag {}
one sig T36_0,T36_1,T36_2 extends Frag {}
one sig T37_0,T37_1,T37_2 extends Frag {}
one sig T38_0,T38_1,T38_2 extends Frag {}
one sig T39_0,T39_1,T39_2 extends Frag {}
one sig T40_0,T40_1 extends Frag {}
one sig T41_0,T41_1,T41_2 extends Frag {}
one sig T42_0 extends Frag {}
one sig T43_0,T43_1 extends Frag {}
one sig T44_0,T44_1 extends Frag {}
one sig T45_0,T45_1 extends Frag {}
one sig T46_0,T46_1 extends Frag {}
one sig T47_0,T47_1 extends Frag {}
one sig T48_0 extends Frag {}
one sig T49_0 extends Frag {}
fact {
  c =
    T0_0 -> 4 + T0_1 -> 2 +
    T1_0 -> 4 +
    T2_0 -> 2 + T2_1 -> 1 + T2_2 -> 1 +
    T3_0 -> 1 + T3_1 -> 4 +
    T4_0 -> 1 + T4_1 -> 2 +
    T5_0 -> 4 +
    T6_0 -> 1 +
    T7_0 -> 1 + T7_1 -> 1 +
    T8_0 -> 1 + T8_1 -> 1 +
    T9_0 -> 5 +
    T10_0 -> 6 +
    T11_0 -> 1 +
    T12_0 -> 1 + T12_1 -> 2 +
    T13_0 -> 1 + T13_1 -> 3 + T13_2 -> 2 +
    T14_0 -> 1 + T14_1 -> 2 +
    T15_0 -> 3 + T15_1 -> 2 + T15_2 -> 1 +
    T16_0 -> 4 + T16_1 -> 1 +
    T17_0 -> 5 +
    T18_0 -> 1 +
    T19_0 -> 1 + T19_1 -> 1 + T19_2 -> 2 +
    T20_0 -> 3 +
    T21_0 -> 1 +
    T22_0 -> 6 +
    T23_0 -> 2 +
    T24_0 -> 2 + T24_1 -> 2 +
    T25_0 -> 3 + T25_1 -> 2 +
    T26_0 -> 1 + T26_1 -> 1 +
    T27_0 -> 6 +
    T28_0 -> 1 + T28_1 -> 1 + T28_2 -> 2 +
    T29_0 -> 4 +
    T30_0 -> 1 + T30_1 -> 1 +
    T31_0 -> 1 +
    T32_0 -> 1 + T32_1 -> 2 + T32_2 -> 2 +
    T33_0 -> 3 + T33_1 -> 2 +
    T34_0 -> 6 +
    T35_0 -> 1 +
    T36_0 -> 2 + T36_1 -> 1 + T36_2 -> 1 +
    T37_0 -> 1 + T37_1 -> 1 + T37_2 -> 2 +
    T38_0 -> 1 + T38_1 -> 1 + T38_2 -> 1 +
    T39_0 -> 1 + T39_1 -> 1 + T39_2 -> 1 +
    T40_0 -> 2 + T40_1 -> 1 +
    T41_0 -> 1 + T41_1 -> 2 + T41_2 -> 1 +
    T42_0 -> 1 +
    T43_0 -> 1 + T43_1 -> 2 +
    T44_0 -> 1 + T44_1 -> 3 +
    T45_0 -> 3 + T45_1 -> 1 +
    T46_0 -> 1 + T46_1 -> 2 +
    T47_0 -> 2 + T47_1 -> 4 +
    T48_0 -> 1 +
    T49_0 -> 1

  prev =
    T0_1 -> T0_0 +
    T2_1 -> T2_0 + T2_2 -> T2_1 +
    T3_1 -> T3_0 +
    T4_1 -> T4_0 +
    T7_1 -> T7_0 +
    T8_1 -> T8_0 +
    T12_1 -> T12_0 +
    T13_1 -> T13_0 + T13_2 -> T13_1 +
    T14_1 -> T14_0 +
    T15_1 -> T15_0 + T15_2 -> T15_1 +
    T16_1 -> T16_0 +
    T19_1 -> T19_0 + T19_2 -> T19_1 +
    T24_1 -> T24_0 +
    T25_1 -> T25_0 +
    T26_1 -> T26_0 +
    T28_1 -> T28_0 + T28_2 -> T28_1 +
    T30_1 -> T30_0 +
    T32_1 -> T32_0 + T32_2 -> T32_1 +
    T33_1 -> T33_0 +
    T36_1 -> T36_0 + T36_2 -> T36_1 +
    T37_1 -> T37_0 + T37_2 -> T37_1 +
    T38_1 -> T38_0 + T38_2 -> T38_1 +
    T39_1 -> T39_0 + T39_2 -> T39_1 +
    T40_1 -> T40_0 +
    T41_1 -> T41_0 + T41_2 -> T41_1 +
    T43_1 -> T43_0 +
    T44_1 -> T44_0 +
    T45_1 -> T45_0 +
    T46_1 -> T46_0 +
    T47_1 -> T47_0
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
