def solution(id_list, report, k):
    target_dict = {}
    
    for user in id_list:
        target_dict[user] = {}
    
    for line in report:
        user, target = line.split()
        target_dict[target][user] = True
        
    answer = [0] * len(id_list)
    for target_value in target_dict.values():
        if len(target_value.keys()) >= k:
            for user_key in target_value:
                answer[id_list.index(user_key)] += 1
    return answer