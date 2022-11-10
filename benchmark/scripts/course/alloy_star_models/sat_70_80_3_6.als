
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39,C40,C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,C51,C52,C53,C54,C55,C56,C57,C58,C59,C60,C61,C62,C63,C64,C65,C66,C67,C68,C69 extends Course {}

fact {
  lectures = C0 -> MonPM + C0 -> WedPM +
    C1 -> MonPM + C1 -> WedPM +
    C2 -> TuePM + C2 -> TuePM +
    C3 -> MonPM + C3 -> WedPM +
    C4 -> MonAM + C4 -> WedAM + C4 -> FriPM +
    C5 -> MonAM + C5 -> WedAM + C5 -> FriPM +
    C6 -> TueAM + C6 -> ThuAM +
    C7 -> MonAM + C7 -> WedAM +
    C8 -> TuePM + C8 -> TuePM +
    C9 -> MonPM + C9 -> WedPM +
    C10 -> MonAM + C10 -> WedAM +
    C11 -> MonAM + C11 -> WedAM + C11 -> FriPM +
    C12 -> TueAM + C12 -> ThuAM +
    C13 -> TueAM + C13 -> ThuAM +
    C14 -> MonPM + C14 -> WedPM +
    C15 -> MonPM + C15 -> WedPM +
    C16 -> MonAM + C16 -> WedAM +
    C17 -> TueAM + C17 -> ThuAM +
    C18 -> MonAM + C18 -> WedAM +
    C19 -> MonPM + C19 -> WedPM +
    C20 -> MonPM + C20 -> WedPM +
    C21 -> TueAM + C21 -> ThuAM +
    C22 -> MonAM + C22 -> WedAM + C22 -> FriPM +
    C23 -> MonAM + C23 -> WedAM +
    C24 -> TueAM + C24 -> ThuAM +
    C25 -> TuePM + C25 -> TuePM +
    C26 -> MonPM + C26 -> WedPM +
    C27 -> TuePM + C27 -> TuePM +
    C28 -> TueAM + C28 -> ThuAM +
    C29 -> MonAM + C29 -> WedAM +
    C30 -> TueAM + C30 -> ThuAM +
    C31 -> TuePM + C31 -> TuePM +
    C32 -> MonPM + C32 -> WedPM +
    C33 -> MonAM + C33 -> WedAM +
    C34 -> TueAM + C34 -> ThuAM +
    C35 -> TueAM + C35 -> ThuAM +
    C36 -> TueAM + C36 -> ThuAM +
    C37 -> TueAM + C37 -> ThuAM +
    C38 -> TuePM + C38 -> TuePM +
    C39 -> MonAM + C39 -> WedAM +
    C40 -> TuePM + C40 -> TuePM +
    C41 -> MonAM + C41 -> WedAM + C41 -> FriPM +
    C42 -> TueAM + C42 -> ThuAM +
    C43 -> MonAM + C43 -> WedAM + C43 -> FriPM +
    C44 -> MonPM + C44 -> WedPM +
    C45 -> MonAM + C45 -> WedAM + C45 -> FriPM +
    C46 -> MonAM + C46 -> WedAM +
    C47 -> TueAM + C47 -> ThuAM +
    C48 -> TuePM + C48 -> TuePM +
    C49 -> TuePM + C49 -> TuePM +
    C50 -> MonPM + C50 -> WedPM +
    C51 -> TuePM + C51 -> TuePM +
    C52 -> TuePM + C52 -> TuePM +
    C53 -> TuePM + C53 -> TuePM +
    C54 -> TueAM + C54 -> ThuAM +
    C55 -> MonAM + C55 -> WedAM +
    C56 -> TueAM + C56 -> ThuAM +
    C57 -> MonAM + C57 -> WedAM + C57 -> FriPM +
    C58 -> TuePM + C58 -> TuePM +
    C59 -> MonPM + C59 -> WedPM +
    C60 -> TueAM + C60 -> ThuAM +
    C61 -> MonPM + C61 -> WedPM +
    C62 -> MonAM + C62 -> WedAM +
    C63 -> MonAM + C63 -> WedAM + C63 -> FriPM +
    C64 -> TueAM + C64 -> ThuAM +
    C65 -> TueAM + C65 -> ThuAM +
    C66 -> MonPM + C66 -> WedPM +
    C67 -> TueAM + C67 -> ThuAM +
    C68 -> MonAM + C68 -> WedAM + C68 -> FriPM +
    C69 -> MonAM + C69 -> WedAM
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
  core = C23
  interests = C36 + C6
}
one sig S1 extends Student {} {
  core = C2 + C7
  interests = C2
}
one sig S2 extends Student {} {
  core = C4 + C54 + C51
  interests = C4
}
one sig S3 extends Student {} {
  core = C41 + C27
  interests = C27 + C65 + C40 + C41 + C43 + C50
}
one sig S4 extends Student {} {
  core = C44 + C41 + C40
  interests = C40 + C62 + C68 + C1
}
one sig S5 extends Student {} {
  core = none
  interests = C38
}
one sig S6 extends Student {} {
  core = C35 + C51 + C11
  interests = C51 + C34 + C19
}
one sig S7 extends Student {} {
  core = C20
  interests = C16 + C60 + C22 + C30 + C58
}
one sig S8 extends Student {} {
  core = C68 + C28
  interests = C28 + C41 + C45
}
one sig S9 extends Student {} {
  core = none
  interests = C52 + C11 + C13 + C33 + C22
}
one sig S10 extends Student {} {
  core = C39
  interests = C27 + C56 + C62
}
one sig S11 extends Student {} {
  core = C3 + C5 + C65
  interests = C65
}
one sig S12 extends Student {} {
  core = C0 + C10 + C12
  interests = C10 + C42 + C46
}
one sig S13 extends Student {} {
  core = C46 + C28 + C58
  interests = C58 + C25 + C37 + C5 + C51
}
one sig S14 extends Student {} {
  core = C25 + C20 + C46
  interests = C20 + C31 + C56 + C5 + C47 + C52
}
one sig S15 extends Student {} {
  core = none
  interests = C17 + C66 + C25 + C4 + C60
}
one sig S16 extends Student {} {
  core = none
  interests = C23 + C29
}
one sig S17 extends Student {} {
  core = C26 + C46
  interests = C26
}
one sig S18 extends Student {} {
  core = none
  interests = C4
}
one sig S19 extends Student {} {
  core = C9
  interests = C11 + C10 + C67 + C48 + C7
}
one sig S20 extends Student {} {
  core = C49
  interests = C23 + C68 + C18 + C37 + C49 + C59
}
one sig S21 extends Student {} {
  core = C59
  interests = C65
}
one sig S22 extends Student {} {
  core = C29
  interests = C25 + C15 + C45 + C63 + C69 + C11
}
one sig S23 extends Student {} {
  core = C11
  interests = C61
}
one sig S24 extends Student {} {
  core = none
  interests = C10 + C62
}
one sig S25 extends Student {} {
  core = C0 + C21 + C18
  interests = C18 + C49 + C44 + C41 + C20 + C13
}
one sig S26 extends Student {} {
  core = C17 + C32 + C18
  interests = C17 + C59 + C33 + C50 + C37
}
one sig S27 extends Student {} {
  core = C26 + C8 + C34
  interests = C34
}
one sig S28 extends Student {} {
  core = C59 + C12
  interests = C59
}
one sig S29 extends Student {} {
  core = C46
  interests = C27 + C18 + C27 + C8 + C28
}
one sig S30 extends Student {} {
  core = C54 + C61 + C62
  interests = C62 + C56
}
one sig S31 extends Student {} {
  core = none
  interests = C1 + C42 + C43 + C23 + C14 + C20
}
one sig S32 extends Student {} {
  core = C0 + C2 + C55
  interests = C2 + C0 + C35 + C58 + C5 + C22
}
one sig S33 extends Student {} {
  core = C8 + C26 + C5
  interests = C5
}
one sig S34 extends Student {} {
  core = C41 + C49
  interests = C49 + C32
}
one sig S35 extends Student {} {
  core = none
  interests = C30
}
one sig S36 extends Student {} {
  core = C33
  interests = C2 + C66 + C67 + C4 + C57
}
one sig S37 extends Student {} {
  core = C34 + C1
  interests = C34 + C24 + C15 + C38
}
one sig S38 extends Student {} {
  core = C35
  interests = C50 + C0 + C59
}
one sig S39 extends Student {} {
  core = C63 + C64 + C40
  interests = C63 + C49 + C18 + C16 + C58
}
one sig S40 extends Student {} {
  core = C27 + C47 + C10
  interests = C47 + C30 + C57 + C67 + C61 + C40
}
one sig S41 extends Student {} {
  core = C30 + C11 + C61
  interests = C11
}
one sig S42 extends Student {} {
  core = C2 + C63
  interests = C2 + C68 + C53
}
one sig S43 extends Student {} {
  core = C18 + C27 + C60
  interests = C18 + C21
}
one sig S44 extends Student {} {
  core = C33 + C52 + C42
  interests = C42 + C28 + C55 + C66
}
one sig S45 extends Student {} {
  core = none
  interests = C38 + C49
}
one sig S46 extends Student {} {
  core = C1 + C16
  interests = C16 + C9 + C41
}
one sig S47 extends Student {} {
  core = C54 + C11 + C8
  interests = C54
}
one sig S48 extends Student {} {
  core = C56 + C43 + C3
  interests = C43
}
one sig S49 extends Student {} {
  core = C40 + C56 + C7
  interests = C40 + C7 + C32 + C63
}
one sig S50 extends Student {} {
  core = C56 + C16 + C32
  interests = C16 + C1
}
one sig S51 extends Student {} {
  core = C32 + C5 + C21
  interests = C32 + C0 + C2 + C66 + C5 + C38
}
one sig S52 extends Student {} {
  core = none
  interests = C19
}
one sig S53 extends Student {} {
  core = C1 + C28
  interests = C1 + C2
}
one sig S54 extends Student {} {
  core = none
  interests = C5
}
one sig S55 extends Student {} {
  core = C41 + C61 + C37
  interests = C41 + C17 + C39 + C44 + C29
}
one sig S56 extends Student {} {
  core = C25 + C45
  interests = C25 + C34 + C45 + C66
}
one sig S57 extends Student {} {
  core = C51
  interests = C36 + C25 + C39 + C17
}
one sig S58 extends Student {} {
  core = C33
  interests = C50 + C23 + C51
}
one sig S59 extends Student {} {
  core = C65 + C40
  interests = C65 + C23 + C52 + C22
}
one sig S60 extends Student {} {
  core = C25 + C69
  interests = C25 + C56 + C0 + C22
}
one sig S61 extends Student {} {
  core = C56
  interests = C10
}
one sig S62 extends Student {} {
  core = C56 + C0 + C38
  interests = C56 + C10 + C15
}
one sig S63 extends Student {} {
  core = none
  interests = C65 + C57 + C46 + C62 + C4
}
one sig S64 extends Student {} {
  core = none
  interests = C41 + C68 + C0 + C2 + C28 + C40
}
one sig S65 extends Student {} {
  core = C59 + C27 + C12
  interests = C59 + C66 + C48 + C32
}
one sig S66 extends Student {} {
  core = C14
  interests = C37 + C16
}
one sig S67 extends Student {} {
  core = none
  interests = C51 + C37 + C40 + C61
}
one sig S68 extends Student {} {
  core = C65 + C2 + C19
  interests = C2
}
one sig S69 extends Student {} {
  core = C30 + C38
  interests = C38 + C40 + C5
}
one sig S70 extends Student {} {
  core = C12
  interests = C22 + C42 + C60 + C57 + C1 + C31
}
one sig S71 extends Student {} {
  core = C65
  interests = C0
}
one sig S72 extends Student {} {
  core = C54 + C39 + C31
  interests = C31 + C65 + C18 + C35 + C44
}
one sig S73 extends Student {} {
  core = C31 + C46
  interests = C46 + C47 + C51 + C20 + C8
}
one sig S74 extends Student {} {
  core = C1 + C11
  interests = C1 + C43 + C28 + C32 + C52 + C18
}
one sig S75 extends Student {} {
  core = none
  interests = C6 + C67 + C3
}
one sig S76 extends Student {} {
  core = none
  interests = C61 + C36
}
one sig S77 extends Student {} {
  core = none
  interests = C45
}
one sig S78 extends Student {} {
  core = none
  interests = C18 + C63 + C14 + C45 + C66
}
one sig S79 extends Student {} {
  core = C3
  interests = C5 + C5 + C42 + C68 + C30
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