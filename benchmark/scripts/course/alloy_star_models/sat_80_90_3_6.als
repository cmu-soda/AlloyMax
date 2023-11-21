
abstract sig Day {}
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {}
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture
}
one sig C0,C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32,C33,C34,C35,C36,C37,C38,C39,C40,C41,C42,C43,C44,C45,C46,C47,C48,C49,C50,C51,C52,C53,C54,C55,C56,C57,C58,C59,C60,C61,C62,C63,C64,C65,C66,C67,C68,C69,C70,C71,C72,C73,C74,C75,C76,C77,C78,C79 extends Course {}

fact {
  lectures = C0 -> MonPM + C0 -> WedPM +
    C1 -> MonAM + C1 -> WedAM + C1 -> FriPM +
    C2 -> MonAM + C2 -> WedAM + C2 -> FriPM +
    C3 -> MonPM + C3 -> WedPM +
    C4 -> MonPM + C4 -> WedPM +
    C5 -> MonAM + C5 -> WedAM +
    C6 -> TuePM + C6 -> TuePM +
    C7 -> MonAM + C7 -> WedAM +
    C8 -> TuePM + C8 -> TuePM +
    C9 -> MonAM + C9 -> WedAM +
    C10 -> MonPM + C10 -> WedPM +
    C11 -> MonAM + C11 -> WedAM + C11 -> FriPM +
    C12 -> TuePM + C12 -> TuePM +
    C13 -> MonAM + C13 -> WedAM +
    C14 -> MonAM + C14 -> WedAM +
    C15 -> MonAM + C15 -> WedAM +
    C16 -> MonPM + C16 -> WedPM +
    C17 -> MonPM + C17 -> WedPM +
    C18 -> MonAM + C18 -> WedAM +
    C19 -> MonAM + C19 -> WedAM + C19 -> FriPM +
    C20 -> MonAM + C20 -> WedAM + C20 -> FriPM +
    C21 -> TuePM + C21 -> TuePM +
    C22 -> MonAM + C22 -> WedAM +
    C23 -> TueAM + C23 -> ThuAM +
    C24 -> MonAM + C24 -> WedAM + C24 -> FriPM +
    C25 -> MonAM + C25 -> WedAM +
    C26 -> TuePM + C26 -> TuePM +
    C27 -> MonAM + C27 -> WedAM +
    C28 -> MonAM + C28 -> WedAM +
    C29 -> MonAM + C29 -> WedAM + C29 -> FriPM +
    C30 -> MonAM + C30 -> WedAM + C30 -> FriPM +
    C31 -> MonAM + C31 -> WedAM +
    C32 -> MonPM + C32 -> WedPM +
    C33 -> TuePM + C33 -> TuePM +
    C34 -> MonAM + C34 -> WedAM +
    C35 -> MonAM + C35 -> WedAM + C35 -> FriPM +
    C36 -> MonAM + C36 -> WedAM + C36 -> FriPM +
    C37 -> MonPM + C37 -> WedPM +
    C38 -> MonPM + C38 -> WedPM +
    C39 -> TuePM + C39 -> TuePM +
    C40 -> MonAM + C40 -> WedAM + C40 -> FriPM +
    C41 -> MonAM + C41 -> WedAM +
    C42 -> MonAM + C42 -> WedAM + C42 -> FriPM +
    C43 -> MonPM + C43 -> WedPM +
    C44 -> MonAM + C44 -> WedAM + C44 -> FriPM +
    C45 -> TueAM + C45 -> ThuAM +
    C46 -> TueAM + C46 -> ThuAM +
    C47 -> MonAM + C47 -> WedAM + C47 -> FriPM +
    C48 -> MonPM + C48 -> WedPM +
    C49 -> TuePM + C49 -> TuePM +
    C50 -> TuePM + C50 -> TuePM +
    C51 -> MonAM + C51 -> WedAM +
    C52 -> MonAM + C52 -> WedAM + C52 -> FriPM +
    C53 -> TueAM + C53 -> ThuAM +
    C54 -> MonPM + C54 -> WedPM +
    C55 -> TuePM + C55 -> TuePM +
    C56 -> MonPM + C56 -> WedPM +
    C57 -> MonAM + C57 -> WedAM +
    C58 -> TuePM + C58 -> TuePM +
    C59 -> MonAM + C59 -> WedAM +
    C60 -> TueAM + C60 -> ThuAM +
    C61 -> MonAM + C61 -> WedAM +
    C62 -> TuePM + C62 -> TuePM +
    C63 -> MonAM + C63 -> WedAM +
    C64 -> MonPM + C64 -> WedPM +
    C65 -> MonAM + C65 -> WedAM +
    C66 -> MonAM + C66 -> WedAM + C66 -> FriPM +
    C67 -> TueAM + C67 -> ThuAM +
    C68 -> TueAM + C68 -> ThuAM +
    C69 -> MonAM + C69 -> WedAM + C69 -> FriPM +
    C70 -> MonPM + C70 -> WedPM +
    C71 -> TuePM + C71 -> TuePM +
    C72 -> MonAM + C72 -> WedAM + C72 -> FriPM +
    C73 -> TuePM + C73 -> TuePM +
    C74 -> MonPM + C74 -> WedPM +
    C75 -> MonPM + C75 -> WedPM +
    C76 -> TuePM + C76 -> TuePM +
    C77 -> MonPM + C77 -> WedPM +
    C78 -> MonPM + C78 -> WedPM +
    C79 -> MonAM + C79 -> WedAM
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
  core = C56 + C7 + C46
  interests = C7 + C73 + C40 + C28
}
one sig S1 extends Student {} {
  core = none
  interests = C58 + C20 + C18 + C30
}
one sig S2 extends Student {} {
  core = C58
  interests = C77 + C17 + C63 + C45
}
one sig S3 extends Student {} {
  core = C5 + C38
  interests = C5
}
one sig S4 extends Student {} {
  core = C75 + C11 + C33
  interests = C11 + C13 + C19 + C61
}
one sig S5 extends Student {} {
  core = none
  interests = C17 + C75
}
one sig S6 extends Student {} {
  core = C59 + C55
  interests = C55 + C19
}
one sig S7 extends Student {} {
  core = none
  interests = C52 + C54 + C56 + C3 + C42
}
one sig S8 extends Student {} {
  core = C67 + C22
  interests = C22 + C29 + C45 + C62 + C12 + C76
}
one sig S9 extends Student {} {
  core = none
  interests = C52
}
one sig S10 extends Student {} {
  core = C67
  interests = C37 + C71 + C1
}
one sig S11 extends Student {} {
  core = C23
  interests = C48 + C45
}
one sig S12 extends Student {} {
  core = none
  interests = C73
}
one sig S13 extends Student {} {
  core = none
  interests = C72 + C61 + C10 + C67
}
one sig S14 extends Student {} {
  core = none
  interests = C36 + C65 + C41 + C69
}
one sig S15 extends Student {} {
  core = C43 + C42 + C67
  interests = C67 + C43 + C12
}
one sig S16 extends Student {} {
  core = C21 + C7 + C17
  interests = C17 + C60 + C64
}
one sig S17 extends Student {} {
  core = C6 + C35
  interests = C35 + C13 + C56
}
one sig S18 extends Student {} {
  core = C62 + C27 + C48
  interests = C62
}
one sig S19 extends Student {} {
  core = C61 + C76
  interests = C76 + C61 + C11
}
one sig S20 extends Student {} {
  core = none
  interests = C63
}
one sig S21 extends Student {} {
  core = C50 + C31
  interests = C50
}
one sig S22 extends Student {} {
  core = none
  interests = C22 + C77 + C60 + C61 + C35 + C49
}
one sig S23 extends Student {} {
  core = C9
  interests = C56 + C7 + C66 + C52 + C56 + C40
}
one sig S24 extends Student {} {
  core = C27 + C26
  interests = C26 + C50 + C69
}
one sig S25 extends Student {} {
  core = C34
  interests = C6 + C2 + C40
}
one sig S26 extends Student {} {
  core = none
  interests = C58 + C52
}
one sig S27 extends Student {} {
  core = none
  interests = C65 + C48 + C21 + C47
}
one sig S28 extends Student {} {
  core = C34 + C10 + C58
  interests = C10 + C20 + C26 + C9 + C1 + C31
}
one sig S29 extends Student {} {
  core = C76 + C22
  interests = C76 + C20
}
one sig S30 extends Student {} {
  core = none
  interests = C34 + C24 + C36 + C21
}
one sig S31 extends Student {} {
  core = C5 + C45 + C73
  interests = C73
}
one sig S32 extends Student {} {
  core = C46
  interests = C32
}
one sig S33 extends Student {} {
  core = C1 + C76 + C46
  interests = C46 + C36
}
one sig S34 extends Student {} {
  core = C46 + C63
  interests = C46
}
one sig S35 extends Student {} {
  core = none
  interests = C34 + C73 + C39 + C11
}
one sig S36 extends Student {} {
  core = none
  interests = C3 + C56 + C48 + C75 + C64
}
one sig S37 extends Student {} {
  core = C31 + C75 + C76
  interests = C31 + C64 + C25 + C0 + C55 + C1
}
one sig S38 extends Student {} {
  core = C71 + C16
  interests = C16 + C21
}
one sig S39 extends Student {} {
  core = C8 + C4 + C35
  interests = C35 + C31 + C6 + C66 + C12
}
one sig S40 extends Student {} {
  core = C68 + C61 + C71
  interests = C61 + C19 + C64 + C10 + C44
}
one sig S41 extends Student {} {
  core = none
  interests = C74 + C70
}
one sig S42 extends Student {} {
  core = none
  interests = C8
}
one sig S43 extends Student {} {
  core = C57 + C77 + C6
  interests = C77 + C76
}
one sig S44 extends Student {} {
  core = none
  interests = C73 + C53 + C25 + C23 + C59 + C70
}
one sig S45 extends Student {} {
  core = C64 + C30 + C12
  interests = C64 + C8
}
one sig S46 extends Student {} {
  core = C15 + C73 + C60
  interests = C73 + C38 + C59 + C63
}
one sig S47 extends Student {} {
  core = C37 + C49 + C23
  interests = C37 + C57
}
one sig S48 extends Student {} {
  core = none
  interests = C22
}
one sig S49 extends Student {} {
  core = none
  interests = C53
}
one sig S50 extends Student {} {
  core = none
  interests = C30 + C6 + C15 + C28
}
one sig S51 extends Student {} {
  core = C29 + C38
  interests = C29 + C14 + C33
}
one sig S52 extends Student {} {
  core = none
  interests = C61 + C54 + C5
}
one sig S53 extends Student {} {
  core = none
  interests = C20 + C51 + C48 + C59 + C75 + C64
}
one sig S54 extends Student {} {
  core = C11 + C39 + C45
  interests = C11 + C11 + C15
}
one sig S55 extends Student {} {
  core = C50 + C11 + C46
  interests = C46 + C14 + C9 + C27
}
one sig S56 extends Student {} {
  core = C52
  interests = C77 + C48 + C15
}
one sig S57 extends Student {} {
  core = C7 + C56
  interests = C56
}
one sig S58 extends Student {} {
  core = C36 + C54
  interests = C54 + C4 + C20
}
one sig S59 extends Student {} {
  core = C29 + C6 + C56
  interests = C6 + C1 + C76 + C13 + C72 + C39
}
one sig S60 extends Student {} {
  core = C61 + C56 + C21
  interests = C61 + C46 + C66
}
one sig S61 extends Student {} {
  core = C8 + C16 + C35
  interests = C16 + C64 + C69 + C76 + C9 + C52
}
one sig S62 extends Student {} {
  core = C1 + C3
  interests = C3 + C17 + C59 + C21 + C57 + C37
}
one sig S63 extends Student {} {
  core = C32 + C66 + C58
  interests = C58 + C63
}
one sig S64 extends Student {} {
  core = C10 + C65
  interests = C10 + C20 + C59
}
one sig S65 extends Student {} {
  core = C73
  interests = C30 + C69 + C14 + C23 + C52
}
one sig S66 extends Student {} {
  core = none
  interests = C29 + C36 + C41 + C77
}
one sig S67 extends Student {} {
  core = none
  interests = C31 + C30
}
one sig S68 extends Student {} {
  core = C22
  interests = C70
}
one sig S69 extends Student {} {
  core = C48
  interests = C47 + C36 + C39 + C64 + C67 + C2
}
one sig S70 extends Student {} {
  core = C8
  interests = C64 + C71
}
one sig S71 extends Student {} {
  core = none
  interests = C25 + C69 + C28 + C53 + C37
}
one sig S72 extends Student {} {
  core = C4
  interests = C62 + C16 + C52 + C61 + C47
}
one sig S73 extends Student {} {
  core = C48 + C44
  interests = C48
}
one sig S74 extends Student {} {
  core = C75 + C76
  interests = C75 + C78 + C19 + C11 + C77
}
one sig S75 extends Student {} {
  core = C61 + C17
  interests = C61 + C39
}
one sig S76 extends Student {} {
  core = none
  interests = C72
}
one sig S77 extends Student {} {
  core = C8
  interests = C72 + C68
}
one sig S78 extends Student {} {
  core = C13
  interests = C37 + C30 + C37
}
one sig S79 extends Student {} {
  core = C18 + C37
  interests = C37 + C23
}
one sig S80 extends Student {} {
  core = C26 + C43
  interests = C26 + C16 + C75 + C20 + C8
}
one sig S81 extends Student {} {
  core = none
  interests = C1 + C75 + C61 + C76
}
one sig S82 extends Student {} {
  core = C6 + C68
  interests = C6 + C38 + C48 + C71 + C53
}
one sig S83 extends Student {} {
  core = C15 + C70 + C71
  interests = C15
}
one sig S84 extends Student {} {
  core = C25 + C56 + C60
  interests = C56 + C77 + C18
}
one sig S85 extends Student {} {
  core = C24
  interests = C6 + C29 + C66 + C8 + C75
}
one sig S86 extends Student {} {
  core = C59 + C45
  interests = C45 + C10 + C27 + C30 + C71 + C34
}
one sig S87 extends Student {} {
  core = C69 + C68
  interests = C68 + C45 + C66 + C2 + C39 + C41
}
one sig S88 extends Student {} {
  core = none
  interests = C31 + C64 + C41 + C24 + C0
}
one sig S89 extends Student {} {
  core = C63 + C76
  interests = C63 + C44 + C20 + C61 + C43
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