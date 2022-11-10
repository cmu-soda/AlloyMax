
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39 extends Course {}

fact {
  lectures = C0 -> MonPM + C0 -> WedPM +
    C1 -> MonPM + C1 -> WedPM +
    C2 -> TueAM + C2 -> ThuAM +
    C3 -> MonPM + C3 -> WedPM +
    C4 -> TuePM + C4 -> TuePM +
    C5 -> TuePM + C5 -> TuePM +
    C6 -> MonPM + C6 -> WedPM +
    C7 -> TuePM + C7 -> TuePM +
    C8 -> MonAM + C8 -> WedAM + C8 -> FriPM +
    C9 -> MonAM + C9 -> WedAM +
    C10 -> TuePM + C10 -> TuePM +
    C11 -> TuePM + C11 -> TuePM +
    C12 -> TueAM + C12 -> ThuAM +
    C13 -> MonPM + C13 -> WedPM +
    C14 -> MonAM + C14 -> WedAM + C14 -> FriPM +
    C15 -> MonAM + C15 -> WedAM +
    C16 -> TuePM + C16 -> TuePM +
    C17 -> MonPM + C17 -> WedPM +
    C18 -> MonAM + C18 -> WedAM +
    C19 -> MonAM + C19 -> WedAM + C19 -> FriPM +
    C20 -> MonAM + C20 -> WedAM + C20 -> FriPM +
    C21 -> MonPM + C21 -> WedPM +
    C22 -> MonAM + C22 -> WedAM +
    C23 -> TuePM + C23 -> TuePM +
    C24 -> MonAM + C24 -> WedAM + C24 -> FriPM +
    C25 -> TueAM + C25 -> ThuAM +
    C26 -> TueAM + C26 -> ThuAM +
    C27 -> MonPM + C27 -> WedPM +
    C28 -> MonAM + C28 -> WedAM +
    C29 -> TueAM + C29 -> ThuAM +
    C30 -> MonAM + C30 -> WedAM +
    C31 -> MonPM + C31 -> WedPM +
    C32 -> MonPM + C32 -> WedPM +
    C33 -> TueAM + C33 -> ThuAM +
    C34 -> TuePM + C34 -> TuePM +
    C35 -> MonAM + C35 -> WedAM +
    C36 -> TuePM + C36 -> TuePM +
    C37 -> MonAM + C37 -> WedAM +
    C38 -> MonAM + C38 -> WedAM + C38 -> FriPM +
    C39 -> TuePM + C39 -> TuePM
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
  core = C26 + C17 + C7
  interests = C17 + C32 + C30 + C2 + C20
}
one sig S1 extends Student {} {
  core = C3 + C39 + C30
  interests = C3 + C6 + C30 + C32 + C10 + C12
}
one sig S2 extends Student {} {
  core = none
  interests = C0 + C11
}
one sig S3 extends Student {} {
  core = C12
  interests = C3 + C29 + C26 + C23 + C10 + C39
}
one sig S4 extends Student {} {
  core = C11 + C9
  interests = C11 + C29
}
one sig S5 extends Student {} {
  core = C38 + C21 + C16
  interests = C38 + C39 + C5 + C17 + C30 + C6
}
one sig S6 extends Student {} {
  core = C32 + C4 + C12
  interests = C4 + C35 + C12 + C33 + C2
}
one sig S7 extends Student {} {
  core = C28 + C2
  interests = C2
}
one sig S8 extends Student {} {
  core = C6 + C29
  interests = C6 + C1 + C28 + C5 + C27
}
one sig S9 extends Student {} {
  core = C29
  interests = C24 + C33 + C24
}
one sig S10 extends Student {} {
  core = none
  interests = C25 + C29 + C10 + C28 + C26
}
one sig S11 extends Student {} {
  core = C12
  interests = C31 + C17 + C1 + C12 + C33 + C6
}
one sig S12 extends Student {} {
  core = C30 + C36 + C13
  interests = C13 + C6
}
one sig S13 extends Student {} {
  core = none
  interests = C4 + C28
}
one sig S14 extends Student {} {
  core = C10
  interests = C30 + C7 + C30
}
one sig S15 extends Student {} {
  core = C31
  interests = C36 + C36 + C23
}
one sig S16 extends Student {} {
  core = C31 + C4
  interests = C4 + C38 + C0 + C5
}
one sig S17 extends Student {} {
  core = none
  interests = C6
}
one sig S18 extends Student {} {
  core = C6 + C15 + C33
  interests = C6 + C36
}
one sig S19 extends Student {} {
  core = none
  interests = C14
}
one sig S20 extends Student {} {
  core = none
  interests = C36
}
one sig S21 extends Student {} {
  core = C14 + C16 + C1
  interests = C14 + C28 + C25 + C22 + C10
}
one sig S22 extends Student {} {
  core = C39
  interests = C1
}
one sig S23 extends Student {} {
  core = C37 + C10 + C2
  interests = C2 + C28
}
one sig S24 extends Student {} {
  core = C25
  interests = C5 + C30 + C23
}
one sig S25 extends Student {} {
  core = C31 + C14 + C39
  interests = C14 + C30 + C17 + C6 + C25
}
one sig S26 extends Student {} {
  core = C5
  interests = C2 + C16 + C5
}
one sig S27 extends Student {} {
  core = C22 + C7
  interests = C7 + C37
}
one sig S28 extends Student {} {
  core = C38 + C5 + C33
  interests = C5 + C34 + C8 + C38 + C15 + C1
}
one sig S29 extends Student {} {
  core = C36 + C38 + C2
  interests = C36 + C27 + C17 + C21 + C2
}
one sig S30 extends Student {} {
  core = C32
  interests = C38
}
one sig S31 extends Student {} {
  core = C22
  interests = C33 + C17 + C38 + C21 + C31
}
one sig S32 extends Student {} {
  core = C23 + C37
  interests = C23
}
one sig S33 extends Student {} {
  core = C28 + C3
  interests = C28 + C8 + C0 + C23 + C14 + C5
}
one sig S34 extends Student {} {
  core = C17 + C24 + C34
  interests = C34
}
one sig S35 extends Student {} {
  core = C29
  interests = C16 + C3 + C39
}
one sig S36 extends Student {} {
  core = none
  interests = C27
}
one sig S37 extends Student {} {
  core = C4 + C25
  interests = C4 + C18 + C11 + C39 + C15 + C4
}
one sig S38 extends Student {} {
  core = none
  interests = C0 + C11 + C17
}
one sig S39 extends Student {} {
  core = C39 + C13
  interests = C39 + C10
}
one sig S40 extends Student {} {
  core = none
  interests = C25 + C28 + C11
}
one sig S41 extends Student {} {
  core = C16
  interests = C15 + C10 + C31 + C11 + C12
}
one sig S42 extends Student {} {
  core = none
  interests = C34
}
one sig S43 extends Student {} {
  core = C6 + C23 + C37
  interests = C37 + C3
}
one sig S44 extends Student {} {
  core = C35 + C12 + C31
  interests = C31 + C38
}
one sig S45 extends Student {} {
  core = C32 + C19 + C25
  interests = C19 + C14 + C13
}
one sig S46 extends Student {} {
  core = C10 + C22 + C3
  interests = C10
}
one sig S47 extends Student {} {
  core = C17
  interests = C8 + C16
}
one sig S48 extends Student {} {
  core = C0 + C26
  interests = C0 + C33 + C1 + C14 + C12 + C10
}
one sig S49 extends Student {} {
  core = C12 + C19
  interests = C12 + C1 + C9 + C11 + C13
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