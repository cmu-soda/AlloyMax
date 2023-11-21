
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39,C40,C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,C51,C52,C53,C54,C55,C56,C57,C58,C59,C60,C61,C62,C63,C64,C65,C66,C67,C68,C69,C70,C71,C72,C73,C74,C75,C76,C77,C78,C79,C80,C81,C82,C83,C84,C85,C86,C87,C88,C89 extends Course {}

fact {
  lectures = C0 -> TueAM + C0 -> ThuAM +
    C1 -> MonPM + C1 -> WedPM +
    C2 -> TuePM + C2 -> TuePM +
    C3 -> MonAM + C3 -> WedAM + C3 -> FriPM +
    C4 -> TueAM + C4 -> ThuAM +
    C5 -> TuePM + C5 -> TuePM +
    C6 -> TuePM + C6 -> TuePM +
    C7 -> TueAM + C7 -> ThuAM +
    C8 -> TuePM + C8 -> TuePM +
    C9 -> TueAM + C9 -> ThuAM +
    C10 -> TuePM + C10 -> TuePM +
    C11 -> TuePM + C11 -> TuePM +
    C12 -> MonAM + C12 -> WedAM +
    C13 -> MonAM + C13 -> WedAM +
    C14 -> MonPM + C14 -> WedPM +
    C15 -> TuePM + C15 -> TuePM +
    C16 -> MonAM + C16 -> WedAM + C16 -> FriPM +
    C17 -> MonAM + C17 -> WedAM + C17 -> FriPM +
    C18 -> TueAM + C18 -> ThuAM +
    C19 -> TuePM + C19 -> TuePM +
    C20 -> MonAM + C20 -> WedAM +
    C21 -> TueAM + C21 -> ThuAM +
    C22 -> MonAM + C22 -> WedAM +
    C23 -> MonAM + C23 -> WedAM + C23 -> FriPM +
    C24 -> MonAM + C24 -> WedAM + C24 -> FriPM +
    C25 -> TuePM + C25 -> TuePM +
    C26 -> MonPM + C26 -> WedPM +
    C27 -> MonAM + C27 -> WedAM +
    C28 -> MonAM + C28 -> WedAM +
    C29 -> TuePM + C29 -> TuePM +
    C30 -> MonAM + C30 -> WedAM +
    C31 -> TuePM + C31 -> TuePM +
    C32 -> MonPM + C32 -> WedPM +
    C33 -> TuePM + C33 -> TuePM +
    C34 -> TueAM + C34 -> ThuAM +
    C35 -> MonPM + C35 -> WedPM +
    C36 -> MonAM + C36 -> WedAM +
    C37 -> TuePM + C37 -> TuePM +
    C38 -> MonAM + C38 -> WedAM + C38 -> FriPM +
    C39 -> MonPM + C39 -> WedPM +
    C40 -> MonPM + C40 -> WedPM +
    C41 -> MonAM + C41 -> WedAM + C41 -> FriPM +
    C42 -> MonAM + C42 -> WedAM +
    C43 -> MonPM + C43 -> WedPM +
    C44 -> TueAM + C44 -> ThuAM +
    C45 -> TueAM + C45 -> ThuAM +
    C46 -> MonPM + C46 -> WedPM +
    C47 -> MonAM + C47 -> WedAM + C47 -> FriPM +
    C48 -> TueAM + C48 -> ThuAM +
    C49 -> TuePM + C49 -> TuePM +
    C50 -> MonAM + C50 -> WedAM + C50 -> FriPM +
    C51 -> MonPM + C51 -> WedPM +
    C52 -> MonAM + C52 -> WedAM +
    C53 -> TuePM + C53 -> TuePM +
    C54 -> TuePM + C54 -> TuePM +
    C55 -> TueAM + C55 -> ThuAM +
    C56 -> MonAM + C56 -> WedAM + C56 -> FriPM +
    C57 -> MonAM + C57 -> WedAM +
    C58 -> TueAM + C58 -> ThuAM +
    C59 -> MonAM + C59 -> WedAM + C59 -> FriPM +
    C60 -> MonAM + C60 -> WedAM +
    C61 -> MonPM + C61 -> WedPM +
    C62 -> MonPM + C62 -> WedPM +
    C63 -> MonPM + C63 -> WedPM +
    C64 -> TuePM + C64 -> TuePM +
    C65 -> TueAM + C65 -> ThuAM +
    C66 -> MonAM + C66 -> WedAM + C66 -> FriPM +
    C67 -> MonAM + C67 -> WedAM + C67 -> FriPM +
    C68 -> MonPM + C68 -> WedPM +
    C69 -> TuePM + C69 -> TuePM +
    C70 -> MonAM + C70 -> WedAM + C70 -> FriPM +
    C71 -> TueAM + C71 -> ThuAM +
    C72 -> TueAM + C72 -> ThuAM +
    C73 -> MonAM + C73 -> WedAM + C73 -> FriPM +
    C74 -> MonAM + C74 -> WedAM +
    C75 -> TueAM + C75 -> ThuAM +
    C76 -> TuePM + C76 -> TuePM +
    C77 -> MonPM + C77 -> WedPM +
    C78 -> TuePM + C78 -> TuePM +
    C79 -> MonPM + C79 -> WedPM +
    C80 -> MonPM + C80 -> WedPM +
    C81 -> MonAM + C81 -> WedAM +
    C82 -> MonAM + C82 -> WedAM + C82 -> FriPM +
    C83 -> MonAM + C83 -> WedAM +
    C84 -> MonPM + C84 -> WedPM +
    C85 -> MonPM + C85 -> WedPM +
    C86 -> TueAM + C86 -> ThuAM +
    C87 -> TueAM + C87 -> ThuAM +
    C88 -> TuePM + C88 -> TuePM +
    C89 -> MonAM + C89 -> WedAM + C89 -> FriPM
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
  core = C54 + C43 + C27
  interests = C54
}
one sig S1 extends Student {} {
  core = C39
  interests = C57 + C58
}
one sig S2 extends Student {} {
  core = C5 + C71
  interests = C5 + C28 + C0 + C15
}
one sig S3 extends Student {} {
  core = C8 + C44
  interests = C8 + C3 + C54 + C80
}
one sig S4 extends Student {} {
  core = C56
  interests = C55 + C24 + C55 + C38 + C37
}
one sig S5 extends Student {} {
  core = C88 + C63
  interests = C88 + C55 + C76 + C73 + C71
}
one sig S6 extends Student {} {
  core = C63 + C20
  interests = C63 + C65 + C81 + C15 + C0
}
one sig S7 extends Student {} {
  core = C57
  interests = C31 + C7
}
one sig S8 extends Student {} {
  core = C15 + C55
  interests = C15 + C89 + C32
}
one sig S9 extends Student {} {
  core = none
  interests = C71 + C16 + C47 + C27 + C45 + C56
}
one sig S10 extends Student {} {
  core = C44 + C13 + C79
  interests = C79 + C84 + C38 + C70
}
one sig S11 extends Student {} {
  core = none
  interests = C68
}
one sig S12 extends Student {} {
  core = C49 + C68 + C16
  interests = C16 + C40 + C31
}
one sig S13 extends Student {} {
  core = C45
  interests = C15 + C30 + C77 + C55
}
one sig S14 extends Student {} {
  core = C35
  interests = C56 + C28
}
one sig S15 extends Student {} {
  core = C70 + C64 + C72
  interests = C64 + C19 + C3 + C18
}
one sig S16 extends Student {} {
  core = C1 + C54
  interests = C1 + C29 + C77
}
one sig S17 extends Student {} {
  core = C31 + C17
  interests = C31 + C45 + C18
}
one sig S18 extends Student {} {
  core = C45
  interests = C32 + C88 + C78
}
one sig S19 extends Student {} {
  core = C19 + C60 + C77
  interests = C77 + C76 + C43
}
one sig S20 extends Student {} {
  core = none
  interests = C16 + C35 + C45
}
one sig S21 extends Student {} {
  core = C50 + C71
  interests = C71 + C54
}
one sig S22 extends Student {} {
  core = C6
  interests = C39 + C22
}
one sig S23 extends Student {} {
  core = none
  interests = C82
}
one sig S24 extends Student {} {
  core = C57
  interests = C77 + C58 + C71 + C14 + C44 + C47
}
one sig S25 extends Student {} {
  core = C65 + C23 + C39
  interests = C23 + C55
}
one sig S26 extends Student {} {
  core = C61 + C86 + C31
  interests = C86 + C11 + C48 + C46
}
one sig S27 extends Student {} {
  core = none
  interests = C80 + C25 + C50
}
one sig S28 extends Student {} {
  core = C45 + C82
  interests = C82 + C22 + C18 + C80 + C63
}
one sig S29 extends Student {} {
  core = none
  interests = C54 + C52 + C45
}
one sig S30 extends Student {} {
  core = C66 + C76 + C7
  interests = C7 + C43 + C39 + C3 + C82 + C14
}
one sig S31 extends Student {} {
  core = C0 + C74
  interests = C74 + C24 + C67 + C79
}
one sig S32 extends Student {} {
  core = C71 + C28 + C80
  interests = C28 + C39
}
one sig S33 extends Student {} {
  core = C63
  interests = C28 + C21 + C87 + C69 + C89
}
one sig S34 extends Student {} {
  core = C7 + C66 + C39
  interests = C7 + C42 + C27 + C68
}
one sig S35 extends Student {} {
  core = C82 + C48 + C14
  interests = C14
}
one sig S36 extends Student {} {
  core = none
  interests = C88 + C10 + C80 + C81
}
one sig S37 extends Student {} {
  core = C59 + C85 + C78
  interests = C85 + C4 + C69
}
one sig S38 extends Student {} {
  core = C15
  interests = C84 + C35
}
one sig S39 extends Student {} {
  core = none
  interests = C45 + C53 + C58 + C81
}
one sig S40 extends Student {} {
  core = C1
  interests = C17 + C17 + C46
}
one sig S41 extends Student {} {
  core = none
  interests = C62 + C57 + C0 + C50
}
one sig S42 extends Student {} {
  core = C24 + C49
  interests = C49
}
one sig S43 extends Student {} {
  core = C46
  interests = C41 + C1 + C85 + C46 + C77 + C20
}
one sig S44 extends Student {} {
  core = none
  interests = C85 + C18 + C57 + C64 + C48
}
one sig S45 extends Student {} {
  core = none
  interests = C73 + C18 + C54
}
one sig S46 extends Student {} {
  core = C44
  interests = C57 + C67 + C79 + C33 + C74
}
one sig S47 extends Student {} {
  core = none
  interests = C0 + C57 + C33 + C83 + C24 + C32
}
one sig S48 extends Student {} {
  core = C74 + C46 + C33
  interests = C46 + C14 + C62 + C13
}
one sig S49 extends Student {} {
  core = none
  interests = C61 + C77 + C17 + C60 + C57 + C78
}
one sig S50 extends Student {} {
  core = C37 + C66
  interests = C66 + C2 + C63
}
one sig S51 extends Student {} {
  core = C76 + C55 + C32
  interests = C32 + C37 + C39
}
one sig S52 extends Student {} {
  core = C45 + C13 + C8
  interests = C13 + C25 + C77 + C58 + C59 + C69
}
one sig S53 extends Student {} {
  core = C2
  interests = C89 + C89 + C14
}
one sig S54 extends Student {} {
  core = C48 + C81 + C69
  interests = C81 + C89 + C81 + C8
}
one sig S55 extends Student {} {
  core = C66
  interests = C32 + C27 + C25 + C21 + C63 + C16
}
one sig S56 extends Student {} {
  core = none
  interests = C7 + C57 + C16 + C28
}
one sig S57 extends Student {} {
  core = none
  interests = C1 + C76 + C41 + C59
}
one sig S58 extends Student {} {
  core = C65
  interests = C81 + C55
}
one sig S59 extends Student {} {
  core = C36
  interests = C58 + C22 + C13 + C58 + C29
}
one sig S60 extends Student {} {
  core = C77 + C4
  interests = C77 + C43 + C47 + C44 + C58 + C74
}
one sig S61 extends Student {} {
  core = C8
  interests = C18 + C4 + C12 + C43 + C5 + C75
}
one sig S62 extends Student {} {
  core = none
  interests = C86
}
one sig S63 extends Student {} {
  core = none
  interests = C32 + C57 + C43
}
one sig S64 extends Student {} {
  core = C57 + C40 + C5
  interests = C5 + C62
}
one sig S65 extends Student {} {
  core = C75 + C54
  interests = C54 + C55 + C39 + C68 + C89
}
one sig S66 extends Student {} {
  core = none
  interests = C12
}
one sig S67 extends Student {} {
  core = C31 + C77
  interests = C77 + C19 + C58
}
one sig S68 extends Student {} {
  core = C80 + C11 + C20
  interests = C20 + C84 + C88
}
one sig S69 extends Student {} {
  core = C4
  interests = C37 + C66 + C82 + C1
}
one sig S70 extends Student {} {
  core = C35 + C49
  interests = C49 + C13 + C30 + C55
}
one sig S71 extends Student {} {
  core = C81
  interests = C21 + C21 + C33 + C82
}
one sig S72 extends Student {} {
  core = none
  interests = C12
}
one sig S73 extends Student {} {
  core = C38 + C33 + C80
  interests = C33 + C56 + C22 + C15
}
one sig S74 extends Student {} {
  core = C44
  interests = C31 + C16 + C84 + C54
}
one sig S75 extends Student {} {
  core = C17 + C86 + C76
  interests = C86
}
one sig S76 extends Student {} {
  core = C58 + C26
  interests = C58 + C7 + C2 + C79 + C65
}
one sig S77 extends Student {} {
  core = none
  interests = C44 + C35 + C32
}
one sig S78 extends Student {} {
  core = C9
  interests = C50
}
one sig S79 extends Student {} {
  core = C59 + C4 + C43
  interests = C43 + C20 + C10 + C89 + C16
}
one sig S80 extends Student {} {
  core = C36 + C31 + C34
  interests = C36
}
one sig S81 extends Student {} {
  core = C10
  interests = C50 + C54 + C50 + C31 + C66 + C17
}
one sig S82 extends Student {} {
  core = C86 + C56 + C2
  interests = C2 + C42 + C17 + C11 + C7 + C46
}
one sig S83 extends Student {} {
  core = none
  interests = C9
}
one sig S84 extends Student {} {
  core = C1 + C7
  interests = C1 + C69 + C50 + C19 + C82 + C9
}
one sig S85 extends Student {} {
  core = C85
  interests = C13 + C53 + C62 + C71 + C43 + C65
}
one sig S86 extends Student {} {
  core = C62 + C2
  interests = C2 + C41 + C50
}
one sig S87 extends Student {} {
  core = C49 + C62
  interests = C49 + C58 + C82 + C10
}
one sig S88 extends Student {} {
  core = C36 + C46
  interests = C36 + C75 + C80
}
one sig S89 extends Student {} {
  core = C48 + C66
  interests = C66
}
one sig S90 extends Student {} {
  core = C53 + C48
  interests = C48 + C65
}
one sig S91 extends Student {} {
  core = C48 + C64 + C14
  interests = C14
}
one sig S92 extends Student {} {
  core = C60
  interests = C8 + C63 + C88 + C45
}
one sig S93 extends Student {} {
  core = C85 + C54 + C38
  interests = C85 + C14 + C11 + C18 + C34 + C53
}
one sig S94 extends Student {} {
  core = C52 + C80 + C21
  interests = C21 + C56
}
one sig S95 extends Student {} {
  core = none
  interests = C31 + C30 + C48
}
one sig S96 extends Student {} {
  core = C59 + C88 + C1
  interests = C1
}
one sig S97 extends Student {} {
  core = C11
  interests = C87 + C59
}
one sig S98 extends Student {} {
  core = C79 + C16 + C87
  interests = C87 + C26
}
one sig S99 extends Student {} {
  core = C35 + C16
  interests = C35 + C6 + C25 + C83 + C82 + C34
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