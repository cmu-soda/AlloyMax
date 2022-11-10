
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39,C40,C41,C42,C43,C44,C45,C46,C47,C48,C49 extends Course {}

fact {
  lectures = C0 -> TuePM + C0 -> TuePM +
    C1 -> MonAM + C1 -> WedAM + C1 -> FriPM +
    C2 -> TuePM + C2 -> TuePM +
    C3 -> TueAM + C3 -> ThuAM +
    C4 -> MonAM + C4 -> WedAM + C4 -> FriPM +
    C5 -> MonAM + C5 -> WedAM + C5 -> FriPM +
    C6 -> TuePM + C6 -> TuePM +
    C7 -> TueAM + C7 -> ThuAM +
    C8 -> TuePM + C8 -> TuePM +
    C9 -> MonPM + C9 -> WedPM +
    C10 -> MonPM + C10 -> WedPM +
    C11 -> MonPM + C11 -> WedPM +
    C12 -> MonAM + C12 -> WedAM +
    C13 -> TueAM + C13 -> ThuAM +
    C14 -> TueAM + C14 -> ThuAM +
    C15 -> TueAM + C15 -> ThuAM +
    C16 -> MonAM + C16 -> WedAM +
    C17 -> TueAM + C17 -> ThuAM +
    C18 -> MonAM + C18 -> WedAM + C18 -> FriPM +
    C19 -> MonAM + C19 -> WedAM + C19 -> FriPM +
    C20 -> MonAM + C20 -> WedAM + C20 -> FriPM +
    C21 -> TuePM + C21 -> TuePM +
    C22 -> MonPM + C22 -> WedPM +
    C23 -> MonPM + C23 -> WedPM +
    C24 -> MonAM + C24 -> WedAM + C24 -> FriPM +
    C25 -> MonAM + C25 -> WedAM +
    C26 -> MonAM + C26 -> WedAM + C26 -> FriPM +
    C27 -> MonPM + C27 -> WedPM +
    C28 -> TuePM + C28 -> TuePM +
    C29 -> TuePM + C29 -> TuePM +
    C30 -> TueAM + C30 -> ThuAM +
    C31 -> MonAM + C31 -> WedAM + C31 -> FriPM +
    C32 -> MonAM + C32 -> WedAM +
    C33 -> TuePM + C33 -> TuePM +
    C34 -> TueAM + C34 -> ThuAM +
    C35 -> TuePM + C35 -> TuePM +
    C36 -> TueAM + C36 -> ThuAM +
    C37 -> MonAM + C37 -> WedAM + C37 -> FriPM +
    C38 -> MonAM + C38 -> WedAM +
    C39 -> MonAM + C39 -> WedAM + C39 -> FriPM +
    C40 -> MonAM + C40 -> WedAM +
    C41 -> TueAM + C41 -> ThuAM +
    C42 -> TuePM + C42 -> TuePM +
    C43 -> TueAM + C43 -> ThuAM +
    C44 -> MonPM + C44 -> WedPM +
    C45 -> MonPM + C45 -> WedPM +
    C46 -> TuePM + C46 -> TuePM +
    C47 -> TueAM + C47 -> ThuAM +
    C48 -> TuePM + C48 -> TuePM +
    C49 -> MonPM + C49 -> WedPM
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
  core = C2 + C1 + C45
  interests = C2 + C48 + C27 + C28 + C37
}
one sig S1 extends Student {} {
  core = none
  interests = C44 + C5
}
one sig S2 extends Student {} {
  core = none
  interests = C7 + C42 + C48 + C16
}
one sig S3 extends Student {} {
  core = C31 + C0 + C14
  interests = C31 + C2 + C22 + C27 + C28 + C38
}
one sig S4 extends Student {} {
  core = C23
  interests = C34 + C30
}
one sig S5 extends Student {} {
  core = C48 + C1 + C10
  interests = C1 + C10 + C48
}
one sig S6 extends Student {} {
  core = none
  interests = C8 + C16
}
one sig S7 extends Student {} {
  core = C33 + C45
  interests = C45
}
one sig S8 extends Student {} {
  core = C28 + C34 + C27
  interests = C34
}
one sig S9 extends Student {} {
  core = C30
  interests = C9 + C9 + C33 + C44
}
one sig S10 extends Student {} {
  core = C27 + C21
  interests = C21 + C29 + C37 + C22 + C21 + C7
}
one sig S11 extends Student {} {
  core = none
  interests = C25 + C12 + C19 + C23 + C27 + C7
}
one sig S12 extends Student {} {
  core = none
  interests = C29 + C4
}
one sig S13 extends Student {} {
  core = C48 + C27 + C19
  interests = C27 + C39 + C8
}
one sig S14 extends Student {} {
  core = C40 + C28
  interests = C40 + C30 + C26 + C38
}
one sig S15 extends Student {} {
  core = C13 + C1 + C33
  interests = C13 + C19 + C10 + C0
}
one sig S16 extends Student {} {
  core = C1 + C41 + C45
  interests = C41 + C35 + C40 + C17 + C28 + C34
}
one sig S17 extends Student {} {
  core = C14
  interests = C39
}
one sig S18 extends Student {} {
  core = none
  interests = C26 + C24 + C11 + C1 + C47 + C5
}
one sig S19 extends Student {} {
  core = C42
  interests = C5 + C45 + C4 + C31 + C17
}
one sig S20 extends Student {} {
  core = none
  interests = C33 + C31 + C40 + C16 + C22
}
one sig S21 extends Student {} {
  core = C43
  interests = C12 + C30 + C34 + C3
}
one sig S22 extends Student {} {
  core = C48
  interests = C44
}
one sig S23 extends Student {} {
  core = C46 + C22 + C37
  interests = C46 + C18 + C38 + C7 + C12 + C31
}
one sig S24 extends Student {} {
  core = C25 + C34 + C46
  interests = C25
}
one sig S25 extends Student {} {
  core = C45 + C7 + C24
  interests = C24 + C11 + C9 + C42 + C43 + C30
}
one sig S26 extends Student {} {
  core = C6 + C43
  interests = C43 + C42 + C25 + C16
}
one sig S27 extends Student {} {
  core = C44 + C36 + C28
  interests = C36 + C13
}
one sig S28 extends Student {} {
  core = C19
  interests = C10 + C10 + C12 + C29
}
one sig S29 extends Student {} {
  core = C36
  interests = C23 + C37 + C43 + C46 + C28
}
one sig S30 extends Student {} {
  core = C26 + C36 + C23
  interests = C23 + C10
}
one sig S31 extends Student {} {
  core = none
  interests = C7 + C11 + C16 + C10 + C28
}
one sig S32 extends Student {} {
  core = C32 + C30 + C10
  interests = C32 + C43 + C11 + C4 + C3 + C13
}
one sig S33 extends Student {} {
  core = C19 + C44
  interests = C19 + C37
}
one sig S34 extends Student {} {
  core = C26 + C17 + C29
  interests = C26
}
one sig S35 extends Student {} {
  core = C24
  interests = C8 + C42 + C30
}
one sig S36 extends Student {} {
  core = C34 + C40
  interests = C40 + C13
}
one sig S37 extends Student {} {
  core = none
  interests = C31 + C26 + C18
}
one sig S38 extends Student {} {
  core = C7 + C29 + C18
  interests = C29 + C11 + C4 + C24
}
one sig S39 extends Student {} {
  core = C18
  interests = C45 + C22 + C46 + C13 + C32 + C48
}
one sig S40 extends Student {} {
  core = C17 + C37
  interests = C17 + C36 + C40 + C13 + C2
}
one sig S41 extends Student {} {
  core = none
  interests = C26
}
one sig S42 extends Student {} {
  core = C12 + C13 + C44
  interests = C44
}
one sig S43 extends Student {} {
  core = C15
  interests = C45 + C26 + C46 + C11 + C15 + C16
}
one sig S44 extends Student {} {
  core = C25 + C9 + C43
  interests = C43 + C7 + C14 + C17
}
one sig S45 extends Student {} {
  core = C31 + C29
  interests = C29 + C29 + C4 + C8 + C1 + C5
}
one sig S46 extends Student {} {
  core = C24 + C23 + C34
  interests = C34 + C25 + C1
}
one sig S47 extends Student {} {
  core = none
  interests = C8 + C42 + C9 + C21 + C6 + C27
}
one sig S48 extends Student {} {
  core = C48 + C20 + C47
  interests = C47 + C49 + C37 + C33 + C9 + C31
}
one sig S49 extends Student {} {
  core = C24
  interests = C15 + C25
}
one sig S50 extends Student {} {
  core = C7
  interests = C21 + C21 + C28 + C4
}
one sig S51 extends Student {} {
  core = none
  interests = C11
}
one sig S52 extends Student {} {
  core = C12
  interests = C17
}
one sig S53 extends Student {} {
  core = C36 + C35
  interests = C35 + C45
}
one sig S54 extends Student {} {
  core = C32 + C48 + C41
  interests = C48 + C39 + C30 + C2 + C42 + C26
}
one sig S55 extends Student {} {
  core = C18 + C49 + C35
  interests = C49 + C45 + C44 + C25 + C38
}
one sig S56 extends Student {} {
  core = C1 + C2
  interests = C2
}
one sig S57 extends Student {} {
  core = C21
  interests = C4 + C46
}
one sig S58 extends Student {} {
  core = C13 + C21
  interests = C13 + C43 + C23 + C31 + C38
}
one sig S59 extends Student {} {
  core = C5 + C48
  interests = C5 + C36 + C29 + C7 + C3 + C10
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