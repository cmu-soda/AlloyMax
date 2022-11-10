
abstract sig Person {
  tags: set Tag
}
one sig P0,P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27,P28,P29 extends Person {}

abstract sig Tag {}
one sig T0,T1,T2,T3,T4,T5,T6,T7 extends Tag {}

fact {
  tags = P1->T1+P1->T3+P1->T4+P1->T6+
    P2->T0+P2->T1+P2->T2+P2->T3+P2->T4+P2->T5+P2->T6+P2->T7+
    P3->T0+P3->T1+P3->T6+
    P4->T2+
    P5->T0+P5->T1+P5->T2+P5->T3+P5->T4+P5->T5+P5->T6+P5->T7+
    P6->T0+P6->T1+P6->T2+P6->T3+
    P7->T1+P7->T3+P7->T4+
    P9->T2+P9->T7+
    P11->T0+P11->T2+P11->T3+P11->T5+P11->T7+
    P12->T2+P12->T3+P12->T7+
    P13->T0+P13->T2+P13->T3+P13->T4+P13->T5+P13->T7+
    P14->T0+P14->T1+P14->T2+P14->T3+P14->T4+P14->T5+P14->T6+P14->T7+
    P15->T6+
    P16->T5+P16->T6+
    P17->T1+P17->T2+P17->T3+P17->T4+P17->T5+
    P18->T1+P18->T3+P18->T4+P18->T6+P18->T7+
    P19->T0+P19->T1+P19->T2+P19->T3+P19->T4+P19->T5+P19->T6+P19->T7+
    P21->T0+P21->T1+P21->T2+P21->T3+P21->T4+P21->T5+P21->T6+P21->T7+
    P22->T0+P22->T1+P22->T2+P22->T3+P22->T4+P22->T5+P22->T6+P22->T7+
    P24->T0+P24->T1+P24->T2+P24->T3+P24->T4+P24->T5+P24->T6+
    P25->T4+
    P26->T0+
    P27->T1+P27->T3+P27->T4+P27->T5+P27->T6+P27->T7+
    P28->T2+P28->T4+P28->T7+
    P29->T2
}

sig Table {
  seat: set Person,
} {
  #seat < 8
  #seat > 2
}

fact {
  all p: Person | one seat.p
}

run {
  no seat': Table -> Person {
    all t: Table | #t.seat' < 8 and #t.seat' > 2
    all p: Person | one seat'.p
    #seat'.tags < #seat.tags
  }
} for exactly 7 Table, 7 int
