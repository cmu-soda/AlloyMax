
abstract sig Person {
  tags: set Tag
}
one sig P0,P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27 extends Person {}

abstract sig Tag {}
one sig T0,T1,T2,T3,T4,T5,T6 extends Tag {}

fact {
  tags = P0->T3+P0->T4+P0->T5+P0->T6+
    P1->T1+P1->T2+
    P2->T3+
    P3->T3+P3->T4+P3->T5+P3->T6+
    P4->T2+P4->T3+P4->T4+P4->T5+
    P5->T0+P5->T1+P5->T5+
    P9->T0+P9->T3+P9->T4+
    P10->T0+P10->T1+P10->T2+P10->T3+P10->T4+P10->T5+P10->T6+
    P11->T0+P11->T2+P11->T3+P11->T5+P11->T6+
    P12->T0+P12->T1+P12->T2+P12->T5+
    P13->T0+P13->T1+P13->T2+P13->T3+P13->T4+P13->T5+P13->T6+
    P14->T0+P14->T1+P14->T6+
    P15->T0+P15->T1+P15->T2+P15->T3+P15->T4+P15->T5+P15->T6+
    P17->T2+P17->T3+P17->T4+P17->T6+
    P18->T0+P18->T1+P18->T2+P18->T3+P18->T4+P18->T5+P18->T6+
    P19->T0+P19->T1+P19->T2+P19->T3+P19->T4+P19->T5+P19->T6+
    P20->T0+P20->T1+P20->T3+P20->T4+P20->T5+P20->T6+
    P21->T0+P21->T3+P21->T4+P21->T5+P21->T6+
    P22->T0+P22->T1+P22->T2+P22->T3+P22->T4+P22->T5+
    P23->T0+P23->T2+P23->T3+P23->T4+P23->T5+
    P25->T2+P25->T3+P25->T4+P25->T6+
    P26->T0+P26->T1+P26->T2+P26->T3+P26->T4+P26->T5+
    P27->T0+P27->T2+P27->T3
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
} for exactly 6 Table, 7 int