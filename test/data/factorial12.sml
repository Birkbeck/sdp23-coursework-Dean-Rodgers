    mov EAX 12
    mov EBX 1
    mov ECX 1
f7: mul EBX EAX
    sub EAX ECX
    jnz EAX f7
    out EBX