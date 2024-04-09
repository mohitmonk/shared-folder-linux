def is_safe(processes, avail, maxm, allot):
    need = [[0] * len(resources) for _ in range(len(processes))]
    finish = [False] * len(processes)
    safe_seq = []
    for i in range(len(processes)):
        for j in range(len(resources)):
            need[i][j] = maxm[i][j] - allot[i][j]
    count = 0
    while count < len(processes):
        found = False
        for p in range(len(processes)):
            if not finish[p]:
                can_allocate = True
                for j in range(len(resources)):
                    if need[p][j] > avail[j]:
                        can_allocate = False
                        break
                if can_allocate:
                    for j in range(len(resources)):
                        avail[j] += allot[p][j]
                    finish[p] = True
                    safe_seq.append(p)
                    found = True
                    count += 1
        if not found:
            break

    return count == len(processes), safe_seq
def banker_algorithm(processes, resources, maxm, allot, avail):
    is_safe_state, safe_sequence = is_safe(processes, avail, maxm, allot)

    if is_safe_state:
        print("System is in safe state.")
        print("Safe sequence is:", safe_sequence)
    else:
        print("System is not in safe state.")


def main():
    processes = ['P0', 'P1', 'P2', 'P3', 'P4']
    resources = ['R0', 'R1', 'R2']
    maxm = [
        [7, 5, 3],
        [3, 2, 2],
        [9, 0, 2],
        [2, 2, 2],
        [4, 3, 3]
    ]
    allot = [
        [0, 1, 0],
        [2, 0, 0],
        [3, 0, 2],
        [2, 1, 1],
        [0, 0, 2]
    ]
    avail = [3, 3, 2]

    banker_algorithm(processes, resources, maxm, allot, avail)


if __name__ == "__main__":
    main()
