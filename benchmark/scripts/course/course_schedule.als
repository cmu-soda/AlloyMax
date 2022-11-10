abstract sig Day {} // A set of days which includes only 5 elements.
one sig Mon, Tue, Wed, Thu, Fri extends Day {}

abstract sig Time {} // A set of times which includes only AM and PM.
one sig AM, PM extends Time {}

abstract sig Course {
  lectures: set Lecture // Each course has several lectures among a week.
} // A set of courses which includes only the following 5 courses.
one sig CS101, Compiler, OS, ML, SE extends Course {}

/**
 *    | Mon   | Tue         | Wed   | Thu         | Fri
 * -------------------------------------------------------
 * AM | CS101 | ML/Compiler | CS101 | ML/Compiler |
 * -------------------------------------------------------
 * PM | SE    | OS          | SE    | OS          | CS101
 */
fact {
  lectures = CS101 -> MonAM + CS101 -> WedAM + CS101 -> FriPM +
    ML -> TueAM + ML -> ThuAM +
    SE -> MonPM + SE -> WedPM +
    Compiler -> TueAM + Compiler -> ThuAM +
    OS -> TuePM + OS -> ThuPM
}

abstract sig Lecture {
  day: one Day,
  time: one Time
} // We have 5 (day) x 2 (AM/PM) different lecture times.
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
  core: set Course, // Each student has to take some core courses.
  interests: set Course, // Each student is interested in some courses.
  courses: set Course // Each student must register some courses.
} // A set of students, here we assume only one student Alice.
one sig Alice extends Student {} {
  core = CS101 // Alice has to take CS101.
  interests = ML + SE // Alice is interested in ML and SE.
}

pred conflict[c1, c2: Course] {
  some l1, l2: Lecture {
    l1 in c1.lectures // l1 is a lecture of course c1.
    l2 in c2.lectures // l2 is a lecture of course c2.
    l1.day = l2.day // l1 and l2 are on the same day.
    l1.time = l2.time // l1 and l2 are at the same time.
  }
}

pred validSchedule[courses: Student -> Course] {
  all stu: Student {
    #stu.courses > 2 // Each student must take at least 3 courses.
    stu.core in stu.courses // Each student must take the core courses.
    all disj c1, c2: stu.courses | not conflict[c1, c2] // No conflicts in lectures
  }
}

// Case 1
run AnySchedule {
  validSchedule[courses]
}

// Case 2.1
run MaxInterests1 {
	validSchedule[courses]
	all stu: Student | maxsome stu.interests & stu.courses
}

// Case 2.2
run MaxInterests2 {
	validSchedule[courses]
	all stu: Student | maxsome comm: set Course |
		comm in stu.interests and comm in stu.courses
}

// Case 3
run WithPrefer {
	validSchedule[courses]
	all stu: Student | maxsome stu.interests & stu.courses
	no lec: Alice.courses.lectures | lec.day = Fri and lec.time = PM
	no lec: Alice.courses.lectures | lec.day = Thu and lec.time = AM
}

// Uncomment the following for Case 4, leave it commented if not running it.
// Case 4
//soft fact {
//	no lec: Alice.courses.lectures | lec.day = Fri and lec.time = PM
//	no lec: Alice.courses.lectures | lec.day = Thu and lec.time = AM
//}
//
//run WithSoftPrefer {
//	validSchedule[courses]
//	all stu: Student | maxsome stu.interests & stu.courses
//}

// Uncomment the following for Case 5, leave it commented if not running it.
// Case 5
//soft fact {
//	no lec: Alice.courses.lectures | lec.day = Fri and lec.time = PM
//	no lec: Alice.courses.lectures | lec.day = Thu and lec.time = AM
//}
//
//run WithSoftPreferAndPrior {
//	validSchedule[courses]
//	all stu: Student | maxsome[1] stu.interests & stu.courses
//}
