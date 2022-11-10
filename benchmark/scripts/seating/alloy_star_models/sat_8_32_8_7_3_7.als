
abstract sig Person {
  tags: set Tag
}
one sig P0,P1,P2,P3,P4,P5,P6,P7,P8,P9,P10,P11,P12,P13,P14,P15,P16,P17,P18,P19,P20,P21,P22,P23,P24,P25,P26,P27,P28,P29,P30,P31 extends Person {}

abstract sig Tag {}
one sig T0,T1,T2,T3,T4,T5,T6,T7 extends Tag {}

fact {
  tags = P0->T1+
    P1->T0+P1->T1+P1->T2+P1->T4+P1->T5+P1->T6+P1->T7+
    P2->T1+P2->T2+P2->T4+
    P4->T0+P4->T1+P4->T2+P4->T3+P4->T4+P4->T5+P4->T6+P4->T7+
    P5->T2+P5->T4+P5->T6+P5->T7+
    P6->T4+P6->T5+P6->T6+P6->T7+
    P7->T2+P7->T6+P7->T7+
    P8->T1+
    P9->T1+P9->T3+P9->T4+P9->T6+P9->T7+
    P10->T0+P10->T3+P10->T6+
    P12->T3+
    P13->T0+P13->T1+P13->T2+P13->T3+P13->T4+P13->T5+P13->T6+
    P14->T0+P14->T1+P14->T2+P14->T5+P14->T6+P14->T7+
    P15->T0+P15->T1+P15->T2+P15->T3+P15->T4+P15->T5+P15->T6+P15->T7+
    P16->T1+P16->T2+P16->T3+P16->T4+P16->T5+P16->T6+
    P17->T0+P17->T1+P17->T2+P17->T3+P17->T5+P17->T7+
    P18->T0+P18->T1+P18->T2+P18->T3+P18->T4+P18->T5+P18->T6+P18->T7+
    P19->T1+P19->T2+P19->T3+P19->T4+P19->T6+P19->T7+
    P20->T0+P20->T5+P20->T6+P20->T7+
    P21->T7+
    P22->T0+P22->T1+P22->T2+P22->T3+P22->T4+P22->T5+P22->T6+P22->T7+
    P23->T1+P23->T7+
    P25->T0+P25->T1+P25->T3+P25->T7+
    P26->T0+P26->T2+P26->T3+P26->T4+P26->T5+P26->T6+
    P27->T0+P27->T2+P27->T3+P27->T4+
    P28->T0+P28->T1+P28->T2+
    P29->T0+P29->T3+P29->T4+P29->T5+P29->T6+P29->T7+
    P30->T4
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
