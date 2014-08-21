package deploy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import data.DataController;

/**
 * �z�M���Ǘ�����N���X
 * 
 * @version 1.0, 20 Aug, 2014
 * @author fsc
 */
public class RuleManager {

	private List<Map<String, List<String>>> dataList;

	/**
	 * �f�t�H���g�R���X�g���N�^
	 */
	public RuleManager() {
		// �R���e���c���X�g�ǂݍ���
		DataController dc = new DataController();
		dataList = dc.readJsonRule();
	}

	/**
	 * �������X�g�Ƀ}�b�`����R���e���c�hD�̃��X�g��ԋp
	 * 
	 * @param attributeList
	 *            �������X�g
	 * @return �R���e���c�h�c���X�g
	 */
	public List<String> getContentsList(List<String> attributeList) {
		List<String> contentsIdList = new ArrayList<String>();

		// �������X�g��z��
		String[] attributes = (String[]) attributeList.toArray(new String[0]);

		// �}�b�`���O����
		for (Map<String, List<String>> rule : dataList) {
			// Jackson�̉e���Ń}�b�v�𕪉�
			for (Map.Entry<String, List<String>> entry : rule.entrySet()) {

				boolean match = true;
				// ���[���̎������������[�U�̑�����񂪖ԗ����Ă��邩�`�F�b�N

				for (String attribute : entry.getValue()) {
					if (Arrays.asList(attributes).contains(attribute)) {
						// �}�b�`���O�p��
						continue;
					} else {
						match = false;
						break;
					}
				}
				// ���ׂă}�b�`�����z�M���[���̃R���e���c�h�c��z�M�Ώۂɒǉ�
				if (match) {
					contentsIdList.add(entry.getKey());
				}
			}
		}

		System.out.println("�y�}�b�`�����R���e���cID���X�g�z");
		for (String contentsId : contentsIdList) {
				System.out.println(contentsId);
		}
		System.out.println("�y�}�b�`�����R���e���cID���X�g�z");

		return contentsIdList;
	}

	/**
	 * �R���e���c�h�c���X�g�̒����烉���_���łЂƂ̃R���e���c�h�c���擾
	 * 
	 * @param contentsIdList
	 *            �R���e���c�h�c���X�g
	 * @return �R���e���c�h�c
	 */
	public String getRandomContentsId(List<String> contentsIdList) {
		if(0 == contentsIdList.size()) {
			return null;
		}
		// Random�N���X�̐���
		Random r = new Random();
		// �����̎擾
		int i = r.nextInt(contentsIdList.size());
		
		System.out.println("�y�����_���ɑI�������R���e���cID�z");
		System.out.println(contentsIdList.get(i));
		System.out.println("�y�����_���ɑI�������R���e���cID�z");

		return contentsIdList.get(i);
	}
}
