
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39,C40,C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,C51,C52,C53,C54,C55,C56,C57,C58,C59 extends Course {}

fact {
  lectures = C0 -> MonPM + C0 -> WedPM +
    C1 -> MonAM + C1 -> WedAM +
    C2 -> MonAM + C2 -> WedAM +
    C3 -> MonAM + C3 -> WedAM + C3 -> FriPM +
    C4 -> TuePM + C4 -> TuePM +
    C5 -> MonAM + C5 -> WedAM +
    C6 -> TueAM + C6 -> ThuAM +
    C7 -> TueAM + C7 -> ThuAM +
    C8 -> TueAM + C8 -> ThuAM +
    C9 -> TuePM + C9 -> TuePM +
    C10 -> MonPM + C10 -> WedPM +
    C11 -> MonAM + C11 -> WedAM +
    C12 -> TueAM + C12 -> ThuAM +
    C13 -> MonAM + C13 -> WedAM + C13 -> FriPM +
    C14 -> MonAM + C14 -> WedAM +
    C15 -> TueAM + C15 -> ThuAM +
    C16 -> MonAM + C16 -> WedAM +
    C17 -> MonAM + C17 -> WedAM +
    C18 -> TueAM + C18 -> ThuAM +
    C19 -> MonAM + C19 -> WedAM + C19 -> FriPM +
    C20 -> MonAM + C20 -> WedAM +
    C21 -> MonPM + C21 -> WedPM +
    C22 -> MonAM + C22 -> WedAM + C22 -> FriPM +
    C23 -> MonAM + C23 -> WedAM + C23 -> FriPM +
    C24 -> MonPM + C24 -> WedPM +
    C25 -> TuePM + C25 -> TuePM +
    C26 -> MonPM + C26 -> WedPM +
    C27 -> TuePM + C27 -> TuePM +
    C28 -> MonPM + C28 -> WedPM +
    C29 -> MonAM + C29 -> WedAM +
    C30 -> TueAM + C30 -> ThuAM +
    C31 -> MonAM + C31 -> WedAM +
    C32 -> TueAM + C32 -> ThuAM +
    C33 -> TueAM + C33 -> ThuAM +
    C34 -> MonAM + C34 -> WedAM + C34 -> FriPM +
    C35 -> MonAM + C35 -> WedAM + C35 -> FriPM +
    C36 -> MonAM + C36 -> WedAM +
    C37 -> MonAM + C37 -> WedAM +
    C38 -> MonAM + C38 -> WedAM + C38 -> FriPM +
    C39 -> MonAM + C39 -> WedAM + C39 -> FriPM +
    C40 -> TuePM + C40 -> TuePM +
    C41 -> MonAM + C41 -> WedAM +
    C42 -> MonPM + C42 -> WedPM +
    C43 -> MonAM + C43 -> WedAM +
    C44 -> TuePM + C44 -> TuePM +
    C45 -> MonAM + C45 -> WedAM + C45 -> FriPM +
    C46 -> MonAM + C46 -> WedAM + C46 -> FriPM +
    C47 -> MonAM + C47 -> WedAM + C47 -> FriPM +
    C48 -> TueAM + C48 -> ThuAM +
    C49 -> MonPM + C49 -> WedPM +
    C50 -> MonPM + C50 -> WedPM +
    C51 -> TuePM + C51 -> TuePM +
    C52 -> TueAM + C52 -> ThuAM +
    C53 -> MonPM + C53 -> WedPM +
    C54 -> MonAM + C54 -> WedAM + C54 -> FriPM +
    C55 -> MonPM + C55 -> WedPM +
    C56 -> MonAM + C56 -> WedAM + C56 -> FriPM +
    C57 -> MonAM + C57 -> WedAM + C57 -> FriPM +
    C58 -> MonAM + C58 -> WedAM +
    C59 -> TueAM + C59 -> ThuAM
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
  core = C35
  interests = C10 + C35
}
one sig S1 extends Student {} {
  core = C34
  interests = C18 + C40 + C42 + C8
}
one sig S2 extends Student {} {
  core = none
  interests = C59
}
one sig S3 extends Student {} {
  core = C23
  interests = C21 + C21 + C5 + C7 + C23 + C0
}
one sig S4 extends Student {} {
  core = C41 + C48
  interests = C41 + C28 + C19
}
one sig S5 extends Student {} {
  core = none
  interests = C53 + C8 + C27 + C2 + C6 + C14
}
one sig S6 extends Student {} {
  core = C22
  interests = C50 + C45 + C24
}
one sig S7 extends Student {} {
  core = C11 + C6 + C49
  interests = C49 + C43 + C56 + C23 + C54
}
one sig S8 extends Student {} {
  core = C35 + C42 + C25
  interests = C42
}
one sig S9 extends Student {} {
  core = C51
  interests = C50 + C50 + C26 + C27
}
one sig S10 extends Student {} {
  core = C14
  interests = C49 + C49
}
one sig S11 extends Student {} {
  core = C8 + C4 + C20
  interests = C20 + C42
}
one sig S12 extends Student {} {
  core = C42 + C46 + C33
  interests = C33 + C34 + C24 + C52 + C43
}
one sig S13 extends Student {} {
  core = C2 + C0
  interests = C0 + C5 + C55 + C26
}
one sig S14 extends Student {} {
  core = C0 + C51
  interests = C0 + C49 + C4 + C41 + C19
}
one sig S15 extends Student {} {
  core = none
  interests = C57 + C43 + C26 + C53 + C3 + C2
}
one sig S16 extends Student {} {
  core = C0
  interests = C5 + C29 + C50 + C4 + C49
}
one sig S17 extends Student {} {
  core = none
  interests = C11 + C23 + C29 + C59 + C21
}
one sig S18 extends Student {} {
  core = none
  interests = C43
}
one sig S19 extends Student {} {
  core = C14 + C52 + C26
  interests = C26 + C19 + C52
}
one sig S20 extends Student {} {
  core = C14
  interests = C25 + C25 + C39 + C41 + C5 + C23
}
one sig S21 extends Student {} {
  core = C47
  interests = C55 + C24 + C23 + C8 + C33
}
one sig S22 extends Student {} {
  core = C6 + C45
  interests = C6 + C0 + C55 + C9 + C30
}
one sig S23 extends Student {} {
  core = C6
  interests = C9 + C11 + C20 + C44
}
one sig S24 extends Student {} {
  core = C13 + C8
  interests = C8 + C24 + C0
}
one sig S25 extends Student {} {
  core = C15 + C3
  interests = C15 + C36 + C49 + C53
}
one sig S26 extends Student {} {
  core = C4 + C58 + C0
  interests = C58 + C55 + C46 + C22 + C23 + C16
}
one sig S27 extends Student {} {
  core = C34
  interests = C7
}
one sig S28 extends Student {} {
  core = C59
  interests = C26 + C28 + C17
}
one sig S29 extends Student {} {
  core = C20 + C15
  interests = C20 + C34 + C25 + C27
}
one sig S30 extends Student {} {
  core = C26 + C5
  interests = C26 + C58 + C19
}
one sig S31 extends Student {} {
  core = C43 + C32 + C21
  interests = C43 + C21 + C26 + C28 + C55
}
one sig S32 extends Student {} {
  core = C16 + C50
  interests = C50 + C59 + C12 + C51 + C30
}
one sig S33 extends Student {} {
  core = C59
  interests = C24 + C59 + C21 + C9 + C31 + C27
}
one sig S34 extends Student {} {
  core = none
  interests = C51 + C45 + C20 + C32
}
one sig S35 extends Student {} {
  core = none
  interests = C39 + C9
}
one sig S36 extends Student {} {
  core = C49 + C9 + C36
  interests = C36 + C45 + C12
}
one sig S37 extends Student {} {
  core = C28
  interests = C29 + C5 + C35 + C44 + C14 + C17
}
one sig S38 extends Student {} {
  core = C34 + C32 + C42
  interests = C42 + C54 + C26 + C40 + C6
}
one sig S39 extends Student {} {
  core = C54
  interests = C21 + C36 + C10 + C15 + C33 + C38
}
one sig S40 extends Student {} {
  core = C9
  interests = C34 + C57 + C1 + C38 + C39 + C54
}
one sig S41 extends Student {} {
  core = C34
  interests = C24
}
one sig S42 extends Student {} {
  core = C21 + C36 + C52
  interests = C36 + C11 + C13 + C12
}
one sig S43 extends Student {} {
  core = C7
  interests = C20 + C55
}
one sig S44 extends Student {} {
  core = none
  interests = C47
}
one sig S45 extends Student {} {
  core = none
  interests = C52 + C53 + C8 + C48
}
one sig S46 extends Student {} {
  core = C52 + C41
  interests = C52 + C41 + C35 + C17 + C5
}
one sig S47 extends Student {} {
  core = C47 + C40 + C21
  interests = C40 + C11 + C45
}
one sig S48 extends Student {} {
  core = C57 + C4 + C18
  interests = C4 + C26 + C41 + C51 + C23 + C58
}
one sig S49 extends Student {} {
  core = C22 + C10 + C12
  interests = C22 + C16 + C33 + C43
}
one sig S50 extends Student {} {
  core = C15
  interests = C20 + C20 + C27 + C2 + C6 + C8
}
one sig S51 extends Student {} {
  core = C17 + C7 + C10
  interests = C10 + C18 + C15
}
one sig S52 extends Student {} {
  core = C2 + C15 + C50
  interests = C2 + C33 + C44
}
one sig S53 extends Student {} {
  core = C47 + C49
  interests = C47 + C26 + C11 + C51
}
one sig S54 extends Student {} {
  core = C27 + C7 + C57
  interests = C7 + C47 + C4
}
one sig S55 extends Student {} {
  core = C21 + C36
  interests = C36 + C16
}
one sig S56 extends Student {} {
  core = C30 + C1 + C28
  interests = C28 + C53 + C48 + C1 + C59
}
one sig S57 extends Student {} {
  core = none
  interests = C49 + C38 + C35 + C41 + C28
}
one sig S58 extends Student {} {
  core = C7 + C46 + C0
  interests = C46 + C57 + C6 + C5 + C39
}
one sig S59 extends Student {} {
  core = C34
  interests = C10 + C10 + C31 + C46 + C50 + C41
}
one sig S60 extends Student {} {
  core = C31
  interests = C28 + C19
}
one sig S61 extends Student {} {
  core = C33
  interests = C26 + C36 + C2 + C48 + C50 + C1
}
one sig S62 extends Student {} {
  core = C38 + C24
  interests = C38 + C0 + C42
}
one sig S63 extends Student {} {
  core = C14 + C42 + C48
  interests = C14 + C20 + C36
}
one sig S64 extends Student {} {
  core = C53 + C2
  interests = C2
}
one sig S65 extends Student {} {
  core = C46 + C8 + C44
  interests = C8 + C35 + C51 + C13 + C26 + C20
}
one sig S66 extends Student {} {
  core = none
  interests = C56 + C1 + C34 + C6
}
one sig S67 extends Student {} {
  core = C31 + C30
  interests = C31 + C38
}
one sig S68 extends Student {} {
  core = C1
  interests = C10 + C41
}
one sig S69 extends Student {} {
  core = none
  interests = C57
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