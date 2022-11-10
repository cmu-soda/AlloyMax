
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29 extends Course {}

fact {
  lectures = C0 -> MonPM + C0 -> WedPM +
    C1 -> TueAM + C1 -> ThuAM +
    C2 -> TueAM + C2 -> ThuAM +
    C3 -> MonPM + C3 -> WedPM +
    C4 -> MonAM + C4 -> WedAM + C4 -> FriPM +
    C5 -> MonPM + C5 -> WedPM +
    C6 -> TuePM + C6 -> TuePM +
    C7 -> TueAM + C7 -> ThuAM +
    C8 -> TueAM + C8 -> ThuAM +
    C9 -> MonAM + C9 -> WedAM +
    C10 -> MonPM + C10 -> WedPM +
    C11 -> TuePM + C11 -> TuePM +
    C12 -> MonPM + C12 -> WedPM +
    C13 -> TuePM + C13 -> TuePM +
    C14 -> TuePM + C14 -> TuePM +
    C15 -> MonAM + C15 -> WedAM + C15 -> FriPM +
    C16 -> TuePM + C16 -> TuePM +
    C17 -> MonAM + C17 -> WedAM +
    C18 -> MonAM + C18 -> WedAM +
    C19 -> MonAM + C19 -> WedAM + C19 -> FriPM +
    C20 -> TueAM + C20 -> ThuAM +
    C21 -> MonAM + C21 -> WedAM + C21 -> FriPM +
    C22 -> TuePM + C22 -> TuePM +
    C23 -> TueAM + C23 -> ThuAM +
    C24 -> TueAM + C24 -> ThuAM +
    C25 -> MonAM + C25 -> WedAM +
    C26 -> TuePM + C26 -> TuePM +
    C27 -> MonAM + C27 -> WedAM +
    C28 -> TuePM + C28 -> TuePM +
    C29 -> TueAM + C29 -> ThuAM
}

abstract sig Lecture {
  day: one Day,
  time: one Time
}
one sig MonAM, MonPM, TueAM, TuePM, WedAM, WedPM,
        ThuAM, ThuPM, FriAM, FriPM extends Lecture {}

fact {
  day = MonAM -> Mon + MonPM -> Mon +
    TueAM -> Tue +TuePM -> Tue +
    WedAM -> Wed + WedPM -> Wed +
    ThuAM -> Thu + ThuPM -> Thu +
    FriAM -> Fri + FriPM -> Fri
  time = MonAM -> AM + MonPM -> PM +
    TueAM -> AM +TuePM -> PM +
    WedAM -> AM + WedPM -> PM +
    ThuAM -> AM + ThuPM -> PM +
    FriAM -> AM + FriPM -> PM
}

abstract sig Student {
  core: set Course,
  interests: set Course,
  courses: set Course
}

one sig S0 extends Student {} {
  core = C15 + C12
  interests = C12 + C2 + C28 + C12 + C6 + C3
}
one sig S1 extends Student {} {
  core = none
  interests = C16
}
one sig S2 extends Student {} {
  core = none
  interests = C17 + C20 + C2
}
one sig S3 extends Student {} {
  core = C23 + C16
  interests = C23 + C19 + C18 + C2 + C21 + C29
}
one sig S4 extends Student {} {
  core = C27 + C8
  interests = C8 + C28
}
one sig S5 extends Student {} {
  core = none
  interests = C18
}
one sig S6 extends Student {} {
  core = none
  interests = C24 + C16
}
one sig S7 extends Student {} {
  core = C18 + C24 + C16
  interests = C18 + C12 + C2
}
one sig S8 extends Student {} {
  core = C4 + C29 + C22
  interests = C29 + C10 + C7
}
one sig S9 extends Student {} {
  core = C16 + C8 + C9
  interests = C16 + C7 + C3 + C24 + C6
}
one sig S10 extends Student {} {
  core = C12 + C16
  interests = C12 + C27 + C23 + C4 + C12
}
one sig S11 extends Student {} {
  core = C22 + C20 + C27
  interests = C22 + C24
}
one sig S12 extends Student {} {
  core = C11 + C29
  interests = C11 + C15 + C7
}
one sig S13 extends Student {} {
  core = none
  interests = C10 + C25 + C3
}
one sig S14 extends Student {} {
  core = C23
  interests = C0 + C0 + C25
}
one sig S15 extends Student {} {
  core = C1 + C21 + C6
  interests = C21 + C20 + C0 + C21 + C13
}
one sig S16 extends Student {} {
  core = C3 + C29
  interests = C29 + C13
}
one sig S17 extends Student {} {
  core = C5 + C28 + C15
  interests = C15
}
one sig S18 extends Student {} {
  core = C10
  interests = C21 + C8 + C19 + C2 + C12
}
one sig S19 extends Student {} {
  core = C14 + C29 + C27
  interests = C29 + C24 + C1
}
one sig S20 extends Student {} {
  core = C19 + C24
  interests = C19 + C6 + C13 + C21
}
one sig S21 extends Student {} {
  core = C13 + C5
  interests = C5 + C17
}
one sig S22 extends Student {} {
  core = C8 + C21
  interests = C8 + C21 + C9
}
one sig S23 extends Student {} {
  core = C14 + C3 + C23
  interests = C23 + C2
}
one sig S24 extends Student {} {
  core = none
  interests = C9 + C1 + C25
}
one sig S25 extends Student {} {
  core = C22 + C1 + C17
  interests = C17 + C26 + C10
}
one sig S26 extends Student {} {
  core = C15 + C0 + C23
  interests = C15 + C10 + C18 + C0 + C12
}
one sig S27 extends Student {} {
  core = C11 + C4 + C12
  interests = C11 + C1 + C7 + C2
}
one sig S28 extends Student {} {
  core = none
  interests = C10
}
one sig S29 extends Student {} {
  core = C28 + C15 + C29
  interests = C29 + C10 + C25
}
one sig S30 extends Student {} {
  core = none
  interests = C8 + C0 + C23
}
one sig S31 extends Student {} {
  core = C10 + C18 + C28
  interests = C28
}
one sig S32 extends Student {} {
  core = C3
  interests = C26 + C26 + C8 + C25
}
one sig S33 extends Student {} {
  core = C11
  interests = C7
}
one sig S34 extends Student {} {
  core = C11 + C27 + C8
  interests = C11 + C0 + C14
}
one sig S35 extends Student {} {
  core = C17
  interests = C14 + C14 + C3 + C0 + C16
}
one sig S36 extends Student {} {
  core = C18 + C29 + C14
  interests = C29
}
one sig S37 extends Student {} {
  core = C10 + C18 + C28
  interests = C28 + C9 + C20 + C10 + C22
}
one sig S38 extends Student {} {
  core = C27
  interests = C13 + C10 + C25 + C0 + C17
}
one sig S39 extends Student {} {
  core = C2
  interests = C25 + C8
}

pred conflict[c1, c2: Course] {
  some l1, l2: Lecture {
    l1 in c1.lectures
    l2 in c2.lectures
    l1.day = l2.day
    l1.time = l2.time
  }
}

pred validSchedule[courses: Student -> Course] {
  all stu: Student {
    #stu.courses > 2
    stu.core in stu.courses
    all disj c1, c2: stu.courses | not conflict[c1, c2]
  }
}

run AnySchedule {
  validSchedule[courses]
  no courses': Student -> Course {
    validSchedule[courses']
    some stu: Student | #(stu.interests & stu.courses) <
      #(stu.interests & stu.courses')
  }
} for 4 Int