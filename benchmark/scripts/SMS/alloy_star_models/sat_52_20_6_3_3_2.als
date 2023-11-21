
abstract sig Task {
  frags: set Frag,
  r: Int,
  d: Int,
  first: Frag,
  final: Frag,
  deps: set Task
}
sig Completed in Task {}

one sig T0,T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,T44,T45,T46,T47,T48,T49,T50,T51 extends Task {}
fact {
  frags =
    T0 -> T0_0 +
    T1 -> T1_0 + T1 -> T1_1 +
    T2 -> T2_0 + T2 -> T2_1 +
    T3 -> T3_0 + T3 -> T3_1 + T3 -> T3_2 +
    T4 -> T4_0 +
    T5 -> T5_0 +
    T6 -> T6_0 + T6 -> T6_1 +
    T7 -> T7_0 + T7 -> T7_1 + T7 -> T7_2 +
    T8 -> T8_0 +
    T9 -> T9_0 +
    T10 -> T10_0 + T10 -> T10_1 + T10 -> T10_2 +
    T11 -> T11_0 +
    T12 -> T12_0 +
    T13 -> T13_0 + T13 -> T13_1 +
    T14 -> T14_0 +
    T15 -> T15_0 + T15 -> T15_1 + T15 -> T15_2 +
    T16 -> T16_0 + T16 -> T16_1 + T16 -> T16_2 +
    T17 -> T17_0 + T17 -> T17_1 +
    T18 -> T18_0 +
    T19 -> T19_0 +
    T20 -> T20_0 + T20 -> T20_1 +
    T21 -> T21_0 + T21 -> T21_1 + T21 -> T21_2 +
    T22 -> T22_0 + T22 -> T22_1 +
    T23 -> T23_0 + T23 -> T23_1 +
    T24 -> T24_0 +
    T25 -> T25_0 +
    T26 -> T26_0 +
    T27 -> T27_0 + T27 -> T27_1 +
    T28 -> T28_0 +
    T29 -> T29_0 +
    T30 -> T30_0 + T30 -> T30_1 +
    T31 -> T31_0 + T31 -> T31_1 +
    T32 -> T32_0 + T32 -> T32_1 + T32 -> T32_2 +
    T33 -> T33_0 + T33 -> T33_1 + T33 -> T33_2 +
    T34 -> T34_0 + T34 -> T34_1 +
    T35 -> T35_0 + T35 -> T35_1 +
    T36 -> T36_0 +
    T37 -> T37_0 + T37 -> T37_1 +
    T38 -> T38_0 +
    T39 -> T39_0 + T39 -> T39_1 +
    T40 -> T40_0 + T40 -> T40_1 + T40 -> T40_2 +
    T41 -> T41_0 +
    T42 -> T42_0 +
    T43 -> T43_0 + T43 -> T43_1 + T43 -> T43_2 +
    T44 -> T44_0 +
    T45 -> T45_0 +
    T46 -> T46_0 + T46 -> T46_1 + T46 -> T46_2 +
    T47 -> T47_0 + T47 -> T47_1 +
    T48 -> T48_0 + T48 -> T48_1 + T48 -> T48_2 +
    T49 -> T49_0 +
    T50 -> T50_0 + T50 -> T50_1 + T50 -> T50_2 +
    T51 -> T51_0 + T51 -> T51_1

  r = T0 -> 18 + T1 -> 4 + T2 -> 5 + T3 -> 14 + T4 -> 10 + T5 -> 20 + T6 -> 16 + T7 -> 9 + T8 -> 19 + T9 -> 16 + T10 -> 16 + T11 -> 14 + T12 -> 4 + T13 -> 19 + T14 -> 1 + T15 -> 6 + T16 -> 7 + T17 -> 10 + T18 -> 18 + T19 -> 14 + T20 -> 14 + T21 -> 8 + T22 -> 1 + T23 -> 18 + T24 -> 19 + T25 -> 7 + T26 -> 18 + T27 -> 8 + T28 -> 14 + T29 -> 11 + T30 -> 13 + T31 -> 14 + T32 -> 5 + T33 -> 19 + T34 -> 15 + T35 -> 3 + T36 -> 3 + T37 -> 7 + T38 -> 3 + T39 -> 7 + T40 -> 18 + T41 -> 12 + T42 -> 15 + T43 -> 0 + T44 -> 18 + T45 -> 20 + T46 -> 7 + T47 -> 12 + T48 -> 8 + T49 -> 8 + T50 -> 12 + T51 -> 3

  d = T0 -> 19 + T1 -> 16 + T2 -> 11 + T3 -> 26 + T4 -> 12 + T5 -> 30 + T6 -> 18 + T7 -> 17 + T8 -> 31 + T9 -> 19 + T10 -> 28 + T11 -> 17 + T12 -> 5 + T13 -> 25 + T14 -> 9 + T15 -> 14 + T16 -> 19 + T17 -> 16 + T18 -> 24 + T19 -> 26 + T20 -> 16 + T21 -> 13 + T22 -> 16 + T23 -> 20 + T24 -> 21 + T25 -> 11 + T26 -> 20 + T27 -> 14 + T28 -> 15 + T29 -> 16 + T30 -> 15 + T31 -> 18 + T32 -> 10 + T33 -> 25 + T34 -> 33 + T35 -> 15 + T36 -> 9 + T37 -> 17 + T38 -> 4 + T39 -> 17 + T40 -> 27 + T41 -> 13 + T42 -> 16 + T43 -> 12 + T44 -> 19 + T45 -> 26 + T46 -> 13 + T47 -> 18 + T48 -> 20 + T49 -> 10 + T50 -> 24 + T51 -> 9

  first = T0 -> T0_0 + T1 -> T1_0 + T2 -> T2_0 + T3 -> T3_0 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_0 + T7 -> T7_0 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_0 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_0 + T14 -> T14_0 + T15 -> T15_0 + T16 -> T16_0 + T17 -> T17_0 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_0 + T21 -> T21_0 + T22 -> T22_0 + T23 -> T23_0 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_0 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_0 + T31 -> T31_0 + T32 -> T32_0 + T33 -> T33_0 + T34 -> T34_0 + T35 -> T35_0 + T36 -> T36_0 + T37 -> T37_0 + T38 -> T38_0 + T39 -> T39_0 + T40 -> T40_0 + T41 -> T41_0 + T42 -> T42_0 + T43 -> T43_0 + T44 -> T44_0 + T45 -> T45_0 + T46 -> T46_0 + T47 -> T47_0 + T48 -> T48_0 + T49 -> T49_0 + T50 -> T50_0 + T51 -> T51_0

  final = T0 -> T0_0 + T1 -> T1_1 + T2 -> T2_1 + T3 -> T3_2 + T4 -> T4_0 + T5 -> T5_0 + T6 -> T6_1 + T7 -> T7_2 + T8 -> T8_0 + T9 -> T9_0 + T10 -> T10_2 + T11 -> T11_0 + T12 -> T12_0 + T13 -> T13_1 + T14 -> T14_0 + T15 -> T15_2 + T16 -> T16_2 + T17 -> T17_1 + T18 -> T18_0 + T19 -> T19_0 + T20 -> T20_1 + T21 -> T21_2 + T22 -> T22_1 + T23 -> T23_1 + T24 -> T24_0 + T25 -> T25_0 + T26 -> T26_0 + T27 -> T27_1 + T28 -> T28_0 + T29 -> T29_0 + T30 -> T30_1 + T31 -> T31_1 + T32 -> T32_2 + T33 -> T33_2 + T34 -> T34_1 + T35 -> T35_1 + T36 -> T36_0 + T37 -> T37_1 + T38 -> T38_0 + T39 -> T39_1 + T40 -> T40_2 + T41 -> T41_0 + T42 -> T42_0 + T43 -> T43_2 + T44 -> T44_0 + T45 -> T45_0 + T46 -> T46_2 + T47 -> T47_1 + T48 -> T48_2 + T49 -> T49_0 + T50 -> T50_2 + T51 -> T51_1

  deps =
    T22 -> T36 +
    T26 -> T11
}

abstract sig Frag {
  s: Int,
  c: Int,
  prev: lone Frag
} {
  s < 33
}
one sig T0_0 extends Frag {}
one sig T1_0,T1_1 extends Frag {}
one sig T2_0,T2_1 extends Frag {}
one sig T3_0,T3_1,T3_2 extends Frag {}
one sig T4_0 extends Frag {}
one sig T5_0 extends Frag {}
one sig T6_0,T6_1 extends Frag {}
one sig T7_0,T7_1,T7_2 extends Frag {}
one sig T8_0 extends Frag {}
one sig T9_0 extends Frag {}
one sig T10_0,T10_1,T10_2 extends Frag {}
one sig T11_0 extends Frag {}
one sig T12_0 extends Frag {}
one sig T13_0,T13_1 extends Frag {}
one sig T14_0 extends Frag {}
one sig T15_0,T15_1,T15_2 extends Frag {}
one sig T16_0,T16_1,T16_2 extends Frag {}
one sig T17_0,T17_1 extends Frag {}
one sig T18_0 extends Frag {}
one sig T19_0 extends Frag {}
one sig T20_0,T20_1 extends Frag {}
one sig T21_0,T21_1,T21_2 extends Frag {}
one sig T22_0,T22_1 extends Frag {}
one sig T23_0,T23_1 extends Frag {}
one sig T24_0 extends Frag {}
one sig T25_0 extends Frag {}
one sig T26_0 extends Frag {}
one sig T27_0,T27_1 extends Frag {}
one sig T28_0 extends Frag {}
one sig T29_0 extends Frag {}
one sig T30_0,T30_1 extends Frag {}
one sig T31_0,T31_1 extends Frag {}
one sig T32_0,T32_1,T32_2 extends Frag {}
one sig T33_0,T33_1,T33_2 extends Frag {}
one sig T34_0,T34_1 extends Frag {}
one sig T35_0,T35_1 extends Frag {}
one sig T36_0 extends Frag {}
one sig T37_0,T37_1 extends Frag {}
one sig T38_0 extends Frag {}
one sig T39_0,T39_1 extends Frag {}
one sig T40_0,T40_1,T40_2 extends Frag {}
one sig T41_0 extends Frag {}
one sig T42_0 extends Frag {}
one sig T43_0,T43_1,T43_2 extends Frag {}
one sig T44_0 extends Frag {}
one sig T45_0 extends Frag {}
one sig T46_0,T46_1,T46_2 extends Frag {}
one sig T47_0,T47_1 extends Frag {}
one sig T48_0,T48_1,T48_2 extends Frag {}
one sig T49_0 extends Frag {}
one sig T50_0,T50_1,T50_2 extends Frag {}
one sig T51_0,T51_1 extends Frag {}
fact {
  c =
    T0_0 -> 1 +
    T1_0 -> 3 + T1_1 -> 1 +
    T2_0 -> 4 + T2_1 -> 2 +
    T3_0 -> 2 + T3_1 -> 3 + T3_2 -> 1 +
    T4_0 -> 1 +
    T5_0 -> 5 +
    T6_0 -> 1 + T6_1 -> 1 +
    T7_0 -> 1 + T7_1 -> 1 + T7_2 -> 2 +
    T8_0 -> 6 +
    T9_0 -> 1 +
    T10_0 -> 1 + T10_1 -> 2 + T10_2 -> 1 +
    T11_0 -> 1 +
    T12_0 -> 1 +
    T13_0 -> 1 + T13_1 -> 1 +
    T14_0 -> 4 +
    T15_0 -> 1 + T15_1 -> 2 + T15_2 -> 1 +
    T16_0 -> 2 + T16_1 -> 1 + T16_2 -> 1 +
    T17_0 -> 1 + T17_1 -> 1 +
    T18_0 -> 6 +
    T19_0 -> 6 +
    T20_0 -> 1 + T20_1 -> 1 +
    T21_0 -> 1 + T21_1 -> 2 + T21_2 -> 2 +
    T22_0 -> 2 + T22_1 -> 3 +
    T23_0 -> 1 + T23_1 -> 1 +
    T24_0 -> 1 +
    T25_0 -> 4 +
    T26_0 -> 1 +
    T27_0 -> 1 + T27_1 -> 1 +
    T28_0 -> 1 +
    T29_0 -> 5 +
    T30_0 -> 1 + T30_1 -> 1 +
    T31_0 -> 1 + T31_1 -> 3 +
    T32_0 -> 1 + T32_1 -> 2 + T32_2 -> 2 +
    T33_0 -> 1 + T33_1 -> 1 + T33_2 -> 1 +
    T34_0 -> 2 + T34_1 -> 4 +
    T35_0 -> 3 + T35_1 -> 3 +
    T36_0 -> 6 +
    T37_0 -> 2 + T37_1 -> 3 +
    T38_0 -> 1 +
    T39_0 -> 1 + T39_1 -> 4 +
    T40_0 -> 1 + T40_1 -> 1 + T40_2 -> 1 +
    T41_0 -> 1 +
    T42_0 -> 1 +
    T43_0 -> 2 + T43_1 -> 2 + T43_2 -> 2 +
    T44_0 -> 1 +
    T45_0 -> 2 +
    T46_0 -> 2 + T46_1 -> 1 + T46_2 -> 3 +
    T47_0 -> 1 + T47_1 -> 1 +
    T48_0 -> 1 + T48_1 -> 2 + T48_2 -> 1 +
    T49_0 -> 2 +
    T50_0 -> 3 + T50_1 -> 2 + T50_2 -> 1 +
    T51_0 -> 1 + T51_1 -> 1

  prev =
    T1_1 -> T1_0 +
    T2_1 -> T2_0 +
    T3_1 -> T3_0 + T3_2 -> T3_1 +
    T6_1 -> T6_0 +
    T7_1 -> T7_0 + T7_2 -> T7_1 +
    T10_1 -> T10_0 + T10_2 -> T10_1 +
    T13_1 -> T13_0 +
    T15_1 -> T15_0 + T15_2 -> T15_1 +
    T16_1 -> T16_0 + T16_2 -> T16_1 +
    T17_1 -> T17_0 +
    T20_1 -> T20_0 +
    T21_1 -> T21_0 + T21_2 -> T21_1 +
    T22_1 -> T22_0 +
    T23_1 -> T23_0 +
    T27_1 -> T27_0 +
    T30_1 -> T30_0 +
    T31_1 -> T31_0 +
    T32_1 -> T32_0 + T32_2 -> T32_1 +
    T33_1 -> T33_0 + T33_2 -> T33_1 +
    T34_1 -> T34_0 +
    T35_1 -> T35_0 +
    T37_1 -> T37_0 +
    T39_1 -> T39_0 +
    T40_1 -> T40_0 + T40_2 -> T40_1 +
    T43_1 -> T43_0 + T43_2 -> T43_1 +
    T46_1 -> T46_0 + T46_2 -> T46_1 +
    T47_1 -> T47_0 +
    T48_1 -> T48_0 + T48_2 -> T48_1 +
    T50_1 -> T50_0 + T50_2 -> T50_1 +
    T51_1 -> T51_0
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
  all f: Frag | f.start < 33
}

run {
  valid[Completed, s]
  all completed': set Task, s': Frag -> Int |
    valid[completed', s'] implies #Completed >= #completed'
} for 7 Int
